//package com.revature.project2.service;
//
//
//import com.revature.project2.dao.RentRepository;
//import com.revature.project2.dao.UserRepository;
//import com.revature.project2.model.Rent;
//import com.revature.project2.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Service
//public class RentService {
//    @Autowired
//    private RentRepository rentRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//    public Rent addRent(int userId){
//
//        User user=userRepository.findById(userId);
//        User liberian=userRepository.findById(1);
//        Rent newRent=new Rent();
//        newRent.setDate(new Date());
//        newRent.setRenter_id(user);
//        newRent.setLiberian_id(liberian);
//        Rent saveRent=rentRepository.save(newRent);
//        return  saveRent;
//
//    }
//}
