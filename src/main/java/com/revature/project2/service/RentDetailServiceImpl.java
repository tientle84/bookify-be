package com.revature.project2.service;

import com.revature.project2.dao.BookRepository;
import com.revature.project2.dao.RentDetailRepository;
import com.revature.project2.dao.RentRepository;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentDetailServiceImpl implements RentDetailService {

    @Autowired
    private RentDetailRepository rentDetailRepository;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createRentDetail(Rent rent, int bookId, LocalDate expiryDate) {

        // pull the book from database
        Book book = bookRepository.findById(bookId).get();

        // update book status to Not Available
        BookStatus bookStatus = new BookStatus(2, "Not Available");
        book.setStatus(bookStatus);
        bookRepository.save(book);

        // create rent detail
        RentDetail rentDetail = new RentDetail(rent, book, expiryDate, null, 0);
        rentDetailRepository.save(rentDetail);
    }

    @Override
    public List<RentDetail> getAllRentDetailByRentId(int id) {
        Rent rent = rentRepository.findById(id).get();
        List<RentDetail> rentDetails = rentDetailRepository.findAllByRentId(rent.getId());
        return rentDetails;
    }

    @Override
    public void returnBookByRentDetailId(int rentDetailId) {
        // pull the rent detail
        RentDetail rentDetail = rentDetailRepository.findById(rentDetailId).get();

        // get the book from rent detail and set the Available status for it
        Book targetBook = rentDetail.getBook();
        targetBook.setStatus(new BookStatus(1, "Available"));
        bookRepository.save(targetBook);

        // calculate fine amount if applicable
        double fineAmount = calculateFineAmount(rentDetail.getExpiryDate(), LocalDate.now());

        // then update the rent detail
        rentDetail.setFineAmount(fineAmount);
        rentDetail.setReturnDate(LocalDate.now());
        rentDetailRepository.save(rentDetail);
    }

    public double calculateFineAmount(LocalDate expiryDate, LocalDate returnDate) {
        if(returnDate.isAfter(expiryDate)) {
            return returnDate.compareTo(expiryDate) * 2; // fine amount will be 2$/1 day
        } else {
            return 0;
        }
    }
}
