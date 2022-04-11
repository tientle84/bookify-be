package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserJwtDto {
    private int userId;
    private String first_name;
    private String last_name;
    private String email;
    private String userRole;
}
