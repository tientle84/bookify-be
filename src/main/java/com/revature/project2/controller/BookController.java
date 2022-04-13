package com.revature.project2.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.model.Book;
import com.revature.project2.model.UserJwtDto;
import com.revature.project2.service.BookService;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @PostMapping()
    public ResponseEntity<?> createBook(@RequestHeader("Authorization") String headerValue,
                           @RequestParam("isbn") String isbn,
                           @RequestParam("title") String title,
                           @RequestParam("author") String author,
                           @RequestParam("publisher") String publisher,
                           @RequestParam("publish_date") String publishDate,
                           @RequestParam("genre") String genre,
                           @RequestParam("image_url") String imageUrl
    ) {

        String jwt=headerValue.split(" ")[1];
        try{
            UserJwtDto dto=jwtService.parseJwt(jwt);
            System.out.println(dto);
            if(dto.getUserRole().equals("liberian")){
                Book book = new Book(isbn, title, author, publisher, publishDate, genre, "Available", imageUrl);
                Book createdBook =  bookService.createBook(book);
                //Book book= bookServiceImpl.addBook(isbn,title,author,publisher,date,genre);
                return ResponseEntity.ok().body(createdBook);
            }
            return ResponseEntity.status(404).body("You are not Authorized to access this end point.");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(500).body("ISBN already exists.");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllBooks(@RequestHeader("Authorization") String headerValue) {
        String jwt=headerValue.split(" ")[1];
        try{
            UserJwtDto dto = jwtService.parseJwt(jwt);
            if(dto != null){
                List<Book> books =  bookService.getAllBooks();
                return ResponseEntity.ok().body(books);
            }

            return ResponseEntity.status(404).body("You are not Authorized to access this end point.");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestHeader("Authorization") String headerValue,
                                        @PathVariable int id,
                                        @RequestParam("isbn") String isbn,
                                        @RequestParam("title") String title,
                                        @RequestParam("author") String author,
                                        @RequestParam("publisher") String publisher,
                                        @RequestParam("publish_date") String publishDate,
                                        @RequestParam("genre") String genre,
                                        @RequestParam("status") String status,
                                        @RequestParam("image_url") String imageUrl
    ) {
        String jwt=headerValue.split(" ")[1];
        try{
            UserJwtDto dto = jwtService.parseJwt(jwt);
            System.out.println(dto);
            if(dto.getUserRole().equals("liberian")){
                Book book = new Book(isbn, title, author, publisher, publishDate, genre, status, imageUrl);
                Book updatedBook =  bookService.updateBook(id, book);
                return ResponseEntity.ok().body(updatedBook);
            }
            return ResponseEntity.status(404).body("You are not authorized to access this end point.");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@RequestHeader("Authorization") String headerValue, @PathVariable int id) {
        if(headerValue != null) {
            String jwt = headerValue.split(" ")[1];

            try{
                UserJwtDto dto = jwtService.parseJwt(jwt);
                if(dto != null && dto.getUserRole().equals("liberian")){
                    bookService.deleteBookById(id);
                    return ResponseEntity.ok().body("Book has been deleted successfully.");
                }

                return ResponseEntity.status(403).body("You are not authorized to access this end point.");
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(500).body(e.getMessage());
            }

        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }


//    @GetMapping("/books/{id}")
//    public ResponseEntity getBookById(@PathVariable int id) throws BookNotFOundException {
//
//        try {
//               Book book= bookService.getBookById(id);
//                return ResponseEntity.ok().body(book);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(404).body(e.getMessage());
//        }
//
//    }



}
