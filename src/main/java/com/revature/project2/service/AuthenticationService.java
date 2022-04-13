package com.revature.project2.service;

import com.revature.project2.dao.UserRepository;
import com.revature.project2.exception.BadParameterException;
import com.revature.project2.exception.FailedLoginException;
import com.revature.project2.exception.FailedRegisterException;
import com.revature.project2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) throws FailedLoginException, BadParameterException {

        if(email.trim().equals("") || password.trim().equals("")) {
            throw new BadParameterException("You must provide username and password to login.");
        }

        try {
            byte[] salt = password.getBytes(StandardCharsets.UTF_8);
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
            String passwordHash = new String(hash, StandardCharsets.UTF_8);

            return userRepository.findByEmailAndPassword(email.trim(), passwordHash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | DataIntegrityViolationException e) {
            throw new FailedLoginException("Invalid username and/or password.");
        }
    }

   public User register(User user) throws FailedRegisterException {
       try {
           byte[] salt = user.getPassword().getBytes(StandardCharsets.UTF_8);
           KeySpec keySpec = new PBEKeySpec(user.getPassword().toCharArray(), salt, 65536, 128);
           SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
           byte[] hash = secretKeyFactory.generateSecret(keySpec).getEncoded();
           String passwordHash = new String(hash, StandardCharsets.UTF_8);
           user.setPassword(passwordHash);

           return userRepository.save(user);
       } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
           throw new FailedRegisterException("Fail to register user.");
       } catch (DataIntegrityViolationException e) {
           throw new FailedRegisterException("Email has already been registered.");
       }
   }
}
