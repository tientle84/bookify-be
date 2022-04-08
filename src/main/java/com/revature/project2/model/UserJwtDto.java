package com.revature.project2.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserJwtDto {
    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private UserRole userRole;
}
