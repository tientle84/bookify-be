package com.revature.project2.controller;

import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDetail;
import com.revature.project2.service.JwtService;
import com.revature.project2.service.RentDetailService;
import com.revature.project2.service.RentService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/rent-details")
public class RentDetailController {

    @Autowired
    private RentDetailService rentDetailService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllRentDetailByRentId(@RequestHeader("Authorization") String headerValue, @PathVariable int id) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {

            String jwt = headerValue.split(" ")[1];
            Jws<Claims> token = jwtService.parseJwt(jwt);
            if(token != null){
                List<RentDetail> rentDetails = rentDetailService.getAllRentDetailByRentId(id);
                return ResponseEntity.ok().body(rentDetails);
            }

            return ResponseEntity.status(404).body("You are not authorized to access this end point.");

        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

}
