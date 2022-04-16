package com.revature.project2.service;

import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDetail;

import java.time.LocalDate;
import java.util.List;

public interface RentDetailService {

    public void createRentDetail(Rent rent, int bookId, LocalDate expiryDate);

    public List<RentDetail> getAllRentDetailByRentId(int id);

    public void returnBookByRentDetailId(int rentDetailId);
}
