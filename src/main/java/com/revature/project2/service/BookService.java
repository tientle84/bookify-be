package com.revature.project2.service;

import com.revature.project2.model.Book;

import java.util.List;

public interface BookService {
    public Book createBook(Book book);

    public Book getBookById(int id);

    public List<Book> getAllBooks();

    public Book updateBook(int id, Book book);

    public void deleteBookById(int bookId);
}
