package com.revature.project2.controller;


import com.revature.project2.exception.BadParamterException;
import com.revature.project2.model.LoginDTO;
import com.revature.project2.model.User;
import com.revature.project2.service.AuthenticationService;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.FailedLoginException;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto){
        try {
            User user = authService.login(dto.getEmailId(), dto.getPassword());
             String jwt=jwtService.createJwt(user);

            HttpHeaders responseHeaders =new HttpHeaders();
            responseHeaders.set("token",jwt);
            return ResponseEntity.ok().headers(responseHeaders).body(user);
        }catch (FailedLoginException e){
            return  ResponseEntity.status(401).body(e.getMessage());
        }catch( BadParamterException e ){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}

