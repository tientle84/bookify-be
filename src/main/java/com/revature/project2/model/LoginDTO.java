package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class LoginDTO {
    private String emailId;
    private String password;
}
