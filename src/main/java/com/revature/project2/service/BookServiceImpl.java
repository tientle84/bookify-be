package com.revature.project2.service;

import com.revature.project2.dao.BookRepository;
import com.revature.project2.exception.BookNotFoundException;
import com.revature.project2.exception.FailedDeleteException;
import com.revature.project2.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found."));
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(int id, Book book) {
        Book targetBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found."));

        targetBook.setIsbn(book.getIsbn());
        targetBook.setTitle(book.getTitle());
        targetBook.setAuthor(book.getAuthor());
        targetBook.setPublisher(book.getPublisher());
        targetBook.setPublishDate(book.getPublishDate());

        // we don't arbitrary update the book status
        // we only update the book status when the book has been rented or returned
        //targetBook.setStatus(book.getStatus());

        targetBook.setGenre(book.getGenre());
        targetBook.setImageUrl(book.getImageUrl());

        return bookRepository.save(targetBook);
    }

    @Override
    public void deleteBookById(int id) {
        Book targetBook = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found."));

        if(targetBook.getStatus().getStatus().equals("Available")) {
            bookRepository.deleteById(id);
        } else {
            throw new FailedDeleteException("The currently rented book cannot be deleted.");
        }
    }
}
