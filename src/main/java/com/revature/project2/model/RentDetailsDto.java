package com.revature.project2.model;


import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RentDetailsDto {
    private int book_id;
    private String expiry_date;
    private String return_date;
    private int amount;
}
