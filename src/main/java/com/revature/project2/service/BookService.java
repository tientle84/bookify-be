package com.revature.project2.service;

import com.revature.project2.exception.BookNotFOundException;
import com.revature.project2.model.BookStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.revature.project2.dao.BookRepository;
import com.revature.project2.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {
@Autowired
    private BookRepository bookRepository;

    /*-----------GET ALL Books--------------*/
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book addBook(String file, String isbn, String title, String author, String publisher, String date,
                    String genre){

    Book b=new Book();

    b.setImage_url(file);
    b.setIsbn(isbn);
    b.setTitle(title);
    b.setAuthor(author);
    b.setPublisher(publisher);
    b.setPublish_date(date);
    b.setGenre(genre);

    BookStatus status=new BookStatus(1,"Available");
    b.setBookStatus(status);
    Book newBook=bookRepository.save(b);
    return  newBook;
}


    public void deleteBookById(int bookId) {
        Book target = bookRepository.getById(bookId);
        bookRepository.delete(target);
    }

    public Book getBookById(int bookId) throws Exception {
        return bookRepository.findById(bookId).orElseThrow(() ->new Exception("Book not found"));
    }

    public boolean getBookStatus(int bookId) throws BookNotFOundException{
        Book book = bookRepository.getById(bookId);
        System.out.println(book.getBookStatus().getBook_status());
        if(book.getBookStatus().getBook_status().equals("Available"))
        {
            return true;
        }

        return false;
    }
}
