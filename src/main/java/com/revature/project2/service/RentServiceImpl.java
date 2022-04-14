package com.revature.project2.service;

import com.revature.project2.dao.RentRepository;
import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.RentNotFoundException;
import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDTO;
import com.revature.project2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Rent createRent(RentDTO rentDto) {

        User manager = userRepository.findById(rentDto.getManagerId()).get();
        User renter = userRepository.findById(rentDto.getRenterId()).get();

        Rent rent = new Rent(LocalDate.now(), manager, renter);
        return rentRepository.save(rent);
    }

//    @Override
//    public Rent getRentById(int id) {
//        Rent rent = rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Rent not found"));
//        return rent;
//    }

    @Override
    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    @Override
    public List<Rent> getAllRentsByRenterId(int id) {
        User render = userRepository.findById(id).get();
        List<Rent> rents = rentRepository.findAllByRenterId(render.getId());
        return rents;
    }

//    @Override
//    public Rent updateRent(int id, Rent rent) {
//        return null;
//    }
//
//    @Override
//    public void deleteRentById(int rentId) {
//
//    }
}
