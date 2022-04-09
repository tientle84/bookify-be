package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class UserDto {
    private String emailId;
    private String first_name;
    private String last_name;
    private UserRole userRole;
    private String user_address;
    private String user_phone;
}
