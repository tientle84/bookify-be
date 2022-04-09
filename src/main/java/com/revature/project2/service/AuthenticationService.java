package com.revature.project2.service;


import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BadParamterException;
import com.revature.project2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User login(String emailId, String password) throws FailedLoginException, BadParamterException {

        if(emailId.trim().equals("") || password.trim().equals("")){
            throw new BadParamterException("You must provide username and passwor to login");
        }
        User user= userRepository.findByEmailIdAndPassword(emailId.trim(),password.trim());
        if(user == null){
            throw new FailedLoginException("Invalid username and/or password");
        }
        return  user;
    }

   public  User register(User user){
      User newUser= userRepository.save(user);
      return  newUser;
   }
}
