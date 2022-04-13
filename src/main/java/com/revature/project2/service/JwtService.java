package com.revature.project2.service;

import com.revature.project2.exception.UnAuthorizedResponse;
import com.revature.project2.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private static JwtService instance;
    private Key key;

    public JwtService(){
        byte[] secret = "my_secret_passwordhkjhkgkgjhghjgkjgkgfgftufutfdsfsdfsdfgsdgsdgdgsdg".getBytes();
        key = Keys.hmacShaKeyFor(secret);
    }

    // singleton implementation
    public static JwtService getInstance() {
        if (JwtService.instance == null) {
            JwtService.instance = new JwtService();
        }

        return JwtService.instance;
    }

    // sign a JWT with the key
    public String createJwt(User user) {
        return Jwts.builder().setSubject(user.getEmail())
                .claim("user_id", user.getId())
                .claim("user_role", user.getRole().getRole())
                .signWith(key)
                .compact();
    }

    // validate a JWT with the key
    public Jws<Claims> parseJwt(String jwt) throws UnAuthorizedResponse {
        try {
            return Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
        } catch(JwtException e) {
            throw new UnAuthorizedResponse("The JWT is not valid.");
        }
    }
}
