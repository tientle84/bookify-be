package com.revature.project2.controller;

import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDTO;
import com.revature.project2.service.JwtService;
import com.revature.project2.service.RentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/rents")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    private RentDetailsService rentDetailsService;
    
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
                List<Rent> rents =  rentService.getAllRentsByRenterId(id);
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
                    Rent createdRent =  rentService.createRent(rentDto);
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
    

//    @PostMapping("/rent_details")
//    public ResponseEntity<?> rent_details(@RequestHeader("Authorization") String headerValue,
//                                          @RequestBody List<RentDetailsDto> rentDetailsDto) {
//
//        String jwt=headerValue.split(" ")[1];
//        try{
//            UserJwtDto dto=jwtService.parseJwt(jwt);
//            System.out.println(dto);
//            if(dto.getUserRole().equals("rentRenter")){
//                Rent rent=rentService.addRent(dto.getUserId());
//                for(RentDetailsDto rentDetails:rentDetailsDto){
//                    rentDetailsService.addRentDetails(rentDetails.getRent_id(),rent,rentDetails.getExpiry_date(),rentDetails.getReturn_date(),rentDetails.getAmount());
//
//                }
//                return ResponseEntity.ok().body("Added");
//            }
//            return ResponseEntity.status(404).body("You are not Authorized to access this end point ");
//        } catch (JsonProcessingException | RentNotFOundException e) {
//            return ResponseEntity.status(500).body(e.getMessage());
//        }
//
//    }

}
