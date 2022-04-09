package com.revature.project2.controller;


import com.revature.project2.model.Book;
import com.revature.project2.service.BookService;
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

    @PostMapping("/addbook")
    public ResponseEntity<?> addBook(@RequestParam("file")MultipartFile file,
                                     @RequestParam("isbn") String isbn,
                                     @RequestParam("title") String title,
                                     @RequestParam("author") String author,
                                     @RequestParam("publisher")String publisher,
                                     @RequestParam("date")String date,
                                     @RequestParam("genre") String genre
                                     ) {
        Book book=bookService.addBook(file,isbn,title,author,publisher,date,genre);
         return ResponseEntity.ok().body(book);

    }

}
