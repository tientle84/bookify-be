package com.revature.project2.controller;


import com.revature.project2.exception.BadParamterException;
import com.revature.project2.model.LoginDTO;
import com.revature.project2.model.User;
import com.revature.project2.model.UserDto;
import com.revature.project2.model.UserRole;
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
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            User user = authService.login(dto.getEmailId(), dto.getPassword());
            String jwt = jwtService.createJwt(user);

            UserDto userDto=new UserDto(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmailId(),
                    user.getUserRole(), user.getPhone());

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("token", jwt);
            return ResponseEntity.ok().headers(responseHeaders).body(userDto);
        } catch (FailedLoginException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        } catch (BadParamterException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public  ResponseEntity<?> register(@RequestBody User user){
        UserRole userRole=new UserRole(2,"bookRenter");
        user.setUserRole(userRole);
        User newUser=authService.register(user);

        UserDto userDto=new UserDto(user.getId(), user.getFirst_name(), user.getLast_name(), user.getEmailId(),
                user.getUserRole(), user.getPhone());

        String jwt=jwtService.createJwt(newUser);
        HttpHeaders responseHeaders=new HttpHeaders();
        responseHeaders.set("token",jwt);
        return  ResponseEntity.ok().headers(responseHeaders).body(userDto);
    }
}





