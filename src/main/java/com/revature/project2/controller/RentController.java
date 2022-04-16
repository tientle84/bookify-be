package com.revature.project2.controller;

import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDTO;
import com.revature.project2.model.RentDetail;
import com.revature.project2.service.JwtService;
import com.revature.project2.service.RentDetailService;
import com.revature.project2.service.RentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private RentDetailService rentDetailService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;

    @GetMapping()
    public ResponseEntity<?> getAllRents(@RequestHeader("Authorization") String headerValue) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {

            String jwt = headerValue.split(" ")[1];
            Jws<Claims> token = jwtService.parseJwt(jwt);
            if(token != null){
                List<Rent> rents =  rentService.getAllRents();
                return ResponseEntity.ok().body(rents);
            }

            return ResponseEntity.status(404).body("You are not authorized to access this end point.");

        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllRentsByRenterId(@RequestHeader("Authorization") String headerValue, @PathVariable int id) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {

            String jwt = headerValue.split(" ")[1];
            Jws<Claims> token = jwtService.parseJwt(jwt);
            if(token != null){
                List<Rent> rents = rentService.getAllRentsByRenterId(id);
                return ResponseEntity.ok().body(rents);
            }

            return ResponseEntity.status(404).body("You are not authorized to access this end point.");

        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @PostMapping()
    public ResponseEntity<?> createRent(@RequestHeader("Authorization") String headerValue, @RequestBody RentDTO rentDto) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {
            String jwt = headerValue.split(" ")[1];

            try{
                Jws<Claims> token = jwtService.parseJwt(jwt);

                if(token != null) {
                    // create rent
                    Rent createdRent = rentService.createRent(rentDto);

                    // create rent detail
                    LocalDate expiryDate = LocalDate.now().plusDays(15);
                    List<Integer> bookIds = rentDto.getBookIds();
                    for(int bookId : bookIds) {
                        rentDetailService.createRentDetail(createdRent, bookId, expiryDate);
                    }

                    return ResponseEntity.ok().body(createdRent);
                }

                throw new UnAuthorizedResponse("This endpoint is used by manager only.");

            } catch (Exception ex) {
                return ResponseEntity.status(500).body(ex.getMessage());
            }
        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }
}
