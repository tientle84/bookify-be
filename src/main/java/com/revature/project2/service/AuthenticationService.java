package com.revature.project2.service;


import com.revature.project2.dao.UserRepository;
import com.revature.project2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User login(String emailId, String password){
        return userRepository.findByEmailIdAndPassword(emailId,password);
    }
}
