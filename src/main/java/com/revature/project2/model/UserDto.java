package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserDto {
    private int id;
    private String first_name;
    private String last_name;
    private String emailId;
    private UserRole userRole;
    private String phone;
}
