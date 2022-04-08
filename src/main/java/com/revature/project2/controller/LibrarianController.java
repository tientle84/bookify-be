package com.revature.project2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.Book;
import com.revature.project2.model.UserJwtDto;
import com.revature.project2.service.BookServiceImpl;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibrarianController {
    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestHeader("Authorization") String headerValue, @RequestBody Book dto){

        String jwt = headerValue.split(" ")[1];

        try {

            UserJwtDto token = jwtService.parseJwt(jwt);

            if (!token.getUserRole().equals("Customer")) {
                throw new UnAuthorizedResponse("You must be a employee to access this endpoint");
            }
            dto =(bookService.addBook(dto));

            return ResponseEntity.ok(dto.toString());

        }  catch (UnAuthorizedResponse | JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body(e.getMessage());
        }


        /*        return ResponseEntity.ok(dto.toString());*/
    }

}
