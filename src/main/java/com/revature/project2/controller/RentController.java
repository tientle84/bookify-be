package com.revature.project2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project2.dao.BookRepository;
import com.revature.project2.model.*;
import com.revature.project2.service.JwtService;
import com.revature.project2.service.RentDetailsService;
import com.revature.project2.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private RentDetailsService rentDetailsService;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/rent_details")
    public ResponseEntity<?> rent_details(@RequestHeader("Authorization") String headerValue,
                                          @RequestBody List<RentDetailsDto> rentDetailsDto)
    {

        String jwt=headerValue.split(" ")[1];
        try{
            UserJwtDto dto=jwtService.parseJwt(jwt);
            System.out.println(dto);
            if(dto.getUserRole().equals("bookRenter")){
                Rent rent=rentService.addRent(dto.getUserId());
                for(RentDetailsDto rentDetails:rentDetailsDto){
                    rentDetailsService.addRentDetails(rentDetails.getBook_id(),rent,rentDetails.getExpiry_date(),rentDetails.getReturn_date(),rentDetails.getAmount());

                }
                return ResponseEntity.ok().body("Added");
            }
            return ResponseEntity.status(404).body("You are not Authorized to access this end point ");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

}
