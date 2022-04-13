package com.revature.project2.controller;

import com.revature.project2.exception.BadParameterException;
import com.revature.project2.exception.FailedLoginException;
import com.revature.project2.exception.FailedRegisterException;
import com.revature.project2.model.*;
import com.revature.project2.service.AuthenticationService;
import com.revature.project2.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*",exposedHeaders = "*",allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            User user = authService.login(dto.getEmail(), dto.getPassword());
            String jwt = jwtService.createJwt(user);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("token", jwt);
            user.setPassword("");

            return ResponseEntity.ok().headers(responseHeaders).body(user);
        } catch (FailedLoginException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (BadParameterException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User createdUser = authService.register(user);
            createdUser.setPassword("");

            return ResponseEntity.ok().body(createdUser);
        } catch (FailedRegisterException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}





