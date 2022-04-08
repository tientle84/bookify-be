package com.revature.project2.controller;


import com.revature.project2.exception.BadParamterException;
import com.revature.project2.model.LoginDTO;
import com.revature.project2.model.User;
import com.revature.project2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        try {
            User user = authService.login(dto.getEmailId(), dto.getPassword());
            return ResponseEntity.ok(user);
        }catch (FailedLoginException e){
            return  ResponseEntity.status(401).body(e.getMessage());
        }catch( BadParamterException e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}

