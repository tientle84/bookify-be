package com.revature.project2.service;

import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentService {

    public Rent createRent(RentDTO rentDto);

    public List<Rent> getAllRents();

    public List<Rent> getAllRentsByRenterId(int id);

}
