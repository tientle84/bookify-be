package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserJwtDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String userRole;
}
