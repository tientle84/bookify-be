package com.revature.project2.service;

import com.revature.project2.dao.BookRepository;
import com.revature.project2.dao.RentDetailRepository;
import com.revature.project2.dao.RentRepository;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.model.Book;
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

        Book book = bookRepository.findById(bookId).get();
        RentDetail rentDetail = new RentDetail(rent, book, expiryDate, null, 0);
        rentDetailRepository.save(rentDetail);
    }

    @Override
    public List<RentDetail> getAllRentDetailByRentId(int id) {
        Rent rent = rentRepository.findById(id).get();
        List<RentDetail> rentDetails = rentDetailRepository.findAllByRentId(rent.getId());
        return rentDetails;
    }

}
