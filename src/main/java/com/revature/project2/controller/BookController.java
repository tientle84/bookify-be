package com.revature.project2.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.model.Book;
import com.revature.project2.model.UserJwtDto;
import com.revature.project2.service.AuthenticationService;
import com.revature.project2.service.BookService;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@CrossOrigin(originPatterns = "*", exposedHeaders = "*", allowedHeaders = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/addbook")
    public ResponseEntity<?> addBook(@RequestHeader("Authorization") String headerValue,
                                     @RequestParam("file") MultipartFile file,
                                     @RequestParam("isbn") String isbn,
                                     @RequestParam("title") String title,
                                     @RequestParam("author") String author,
                                     @RequestParam("publisher") String publisher,
                                     @RequestParam("date") String date,
                                     @RequestParam("genre") String genre
    ) {

        String jwt = headerValue.split(" ")[1];
        try {
            UserJwtDto dto = jwtService.parseJwt(jwt);
            System.out.println(dto);
            if (dto.getUserRole().equals("liberian")) {
                Book book = bookService.addBook(file, isbn, title, author, publisher, date, genre);
                return ResponseEntity.ok().body(book);
            }
            return ResponseEntity.status(404).body("You are not Authorized to access this end point ");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/books")
    public List<Book> getAllBook() {
        List<Book> books = bookService.getAllBooks();
        return books;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@RequestHeader("Authorization") String headerValue, @PathVariable int id) {


        try {
            String jwt = headerValue.split(" ")[1];
            UserJwtDto dto = jwtService.parseJwt(jwt);

            System.out.println(dto);

            if (!(dto == null)) {
                try {
                    if (bookService.getBookById(id) != null && bookService.getBookStatus(id)!=false) {
                        System.out.println(bookService.getBookById(id).toString());
                        bookService.deleteBookById(id);
                        return ResponseEntity.ok().body(" Deleted");
                    }
                } catch (Exception e) {
                    return ResponseEntity.status(404).body(e.getMessage());
                }
            }
            return ResponseEntity.status(400).body("Book is Rented ");

        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }


    @GetMapping("/books/{id}")
    public ResponseEntity getBookById(@PathVariable int id) throws Exception {

        try {
               Book book= bookService.getBookById(id);
                return ResponseEntity.ok().body(book);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }

    }



}
