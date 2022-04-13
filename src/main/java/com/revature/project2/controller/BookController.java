package com.revature.project2.controller;


import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BookNotFoundException;
import com.revature.project2.exception.FailedDeleteException;
import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.service.BookService;
import com.revature.project2.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
@RequestMapping("/books")
public class BookController {

    private String authorization = "Authorization";
    private String userRole = "user_role";

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
    ) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {
            String jwt = headerValue.split(" ")[1];

            try{
                Jws<Claims> token = jwtService.parseJwt(jwt);
                System.out.println(token);

                if(token.getBody().get(userRole).equals("renter")) {
                    throw new UnAuthorizedResponse("This endpoint is used by manager only.");
                }

                // new added book has default Available status
                BookStatus status = new BookStatus(1, "Available");
                System.out.println(status);

                Book book = new Book(isbn, title, author, publisher, publishDate, genre, status, imageUrl);
                Book createdBook =  bookService.createBook(book);
                return ResponseEntity.ok().body(createdBook);

            } catch (DataIntegrityViolationException ex) {
                return ResponseEntity.status(500).body("ISBN already exists.");
            }
        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllBooks(@RequestHeader("Authorization") String headerValue) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {

                String jwt = headerValue.split(" ")[1];
                Jws<Claims> token = jwtService.parseJwt(jwt);
                if(token != null){
                    List<Book> books =  bookService.getAllBooks();
                    return ResponseEntity.ok().body(books);
                }

                return ResponseEntity.status(404).body("You are not authorized to access this end point.");

        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestHeader("Authorization") String headerValue, @PathVariable int id, @RequestBody Book book) throws UnAuthorizedResponse {
        if(!headerValue.equals(null) && !headerValue.equals("")) {
            String jwt = headerValue.split(" ")[1];
            Jws<Claims> token = jwtService.parseJwt(jwt);

            if(token.getBody().get(userRole).equals("renter")) {
                throw new UnAuthorizedResponse("This endpoint is used by manager only.");
            }

            Book updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok().body(updatedBook);
        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookById(@RequestHeader("Authorization") String headerValue, @PathVariable int id) {
        if(!headerValue.equals(null) && !headerValue.equals("")) {
            String jwt = headerValue.split(" ")[1];

            try{
                Jws<Claims> token = jwtService.parseJwt(jwt);

                if(token.getBody().get(userRole).equals("renter")) {
                    throw new UnAuthorizedResponse("This endpoint is used by manager only.");
                }

                bookService.deleteBookById(id);
                return ResponseEntity.ok().body("Book has been deleted successfully.");

            } catch (UnAuthorizedResponse e) {
                return ResponseEntity.status(500).body(e.getMessage());
            } catch (FailedDeleteException ex) {
                return ResponseEntity.status(500).body(ex.getMessage());
            }
        } else {
            return ResponseEntity.status(400).body("You have to login to use this function.");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBookById(@PathVariable String id) {
        int intId;

        try {
            intId = Integer.parseInt(id);
            Book book = bookService.getBookById(intId);
            return ResponseEntity.ok().body(book);
        } catch (BookNotFoundException bnfException) {
            return ResponseEntity.status(400).body(bnfException.getMessage());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("Invalid book id was provided.");
        }
    }
}
