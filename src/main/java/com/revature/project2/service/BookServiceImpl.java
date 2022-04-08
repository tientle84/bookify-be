package com.revature.project2.service;

import com.revature.project2.model.Book;
import com.revature.project2.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    BookRepository bookRepository;
    @Override
    public Book addBook(Book b) {
        Book newBook = bookRepository.save(b);
        return null;
    }
}
