package com.revature.project2.service;


import com.revature.project2.dao.BookRepository;
import com.revature.project2.dao.RentDetailsRepository;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BookNotFOundException;
import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentDetailsService {
    @Autowired
    private RentDetailsRepository rentDetailsRepository;
    @Autowired
    private BookRepository bookRepository;

public void addRentDetails(int bookId,Rent rentId,String expiryDate,String return_date,int fineAmount) throws BookNotFOundException{
    Book book=bookRepository.findById(bookId).orElseThrow(()-> new BookNotFOundException("Book Not Found"));
    BookStatus bookStatus=new BookStatus();
    bookStatus.setId(2);
    bookStatus.setBook_status("Not Available");
    book.setBookStatus(bookStatus);
    Book newBook=bookRepository.save(book);

    RentDetails rentDetails=new RentDetails();
    rentDetails.setBook_id(newBook);
    rentDetails.setRent_id(rentId);
    rentDetails.setExpiry_date(expiryDate);
    rentDetails.setReturn_date(return_date);
    rentDetails.setFinal_amount(fineAmount);
    rentDetailsRepository.save(rentDetails);

}
}
