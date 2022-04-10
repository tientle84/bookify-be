package com.revature.project2.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project2.model.Book;
import com.revature.project2.model.LoginDTO;
import com.revature.project2.model.UserJwtDto;
import com.revature.project2.service.BookService;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/addbook")
    public ResponseEntity<?> addBook(@RequestHeader("Authorization") String headerValue,
                                     @RequestParam("file")MultipartFile file,
                                     @RequestParam("isbn") String isbn,
                                     @RequestParam("title") String title,
                                     @RequestParam("author") String author,
                                     @RequestParam("publisher")String publisher,
                                     @RequestParam("date")String date,
                                     @RequestParam("genre") String genre
                                     )  {

        String jwt=headerValue.split(" ")[1];
        try{
            UserJwtDto dto=jwtService.parseJwt(jwt);
            System.out.println(dto);
            if(dto.getUserRole().equals("liberian")){
                Book book=bookService.addBook(file,isbn,title,author,publisher,date,genre);
                return ResponseEntity.ok().body(book);
            }
           return ResponseEntity.status(404).body("You are not Authorized to access this end point ");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
