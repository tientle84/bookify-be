package com.revature.project2.service;

import com.revature.project2.dao.BookRepository;
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
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(int id, Book book) {
        Book targetBook = bookRepository.findById(id).get();

        targetBook.setIsbn(book.getIsbn());
        targetBook.setTitle(book.getTitle());
        targetBook.setAuthor(book.getAuthor());
        targetBook.setPublisher(book.getPublisher());
        targetBook.setPublishDate(book.getPublishDate());
        targetBook.setStatus(book.getStatus());
        targetBook.setGenre(book.getGenre());
        targetBook.setImageUrl(book.getImageUrl());

        return bookRepository.save(targetBook);
    }

    @Override
    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }


//    public Book addBook(MultipartFile file, String isbn, String title, String author, String publisher, String date,
//                        String genre){
//
//        Book b=new Book();
//        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains("..")){
//            System.out.println("not a valid file");
//        }
//        try{
//            b.setImage_url(Base64.getEncoder().encodeToString(file.getBytes()));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        b.setIsbn(isbn);
//        b.setTitle(title);
//        b.setAuthor(author);
//        b.setPublisher(publisher);
//        b.setPublish_date(date);
//        b.setGenre(genre);
//
//        BookStatus status=new BookStatus(1,"Available");
//        b.setBookStatus(status);
//        Book newBook=bookRepository.save(b);
//        return  newBook;
//    }



}
