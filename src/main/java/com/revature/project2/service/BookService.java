package com.revature.project2.service;

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

@Service
public class BookService {
@Autowired
    private BookRepository bookRepository;


public Book addBook(MultipartFile file, String isbn, String title, String author, String publisher, String date,
                    String genre){

    Book b=new Book();
    String fileName= StringUtils.cleanPath(file.getOriginalFilename());
    if(fileName.contains("..")){
        System.out.println("not a valid file");
    }
    try{
        b.setImage_url(Base64.getEncoder().encodeToString(file.getBytes()));
    }catch (IOException e){
        e.printStackTrace();
    }
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


}
