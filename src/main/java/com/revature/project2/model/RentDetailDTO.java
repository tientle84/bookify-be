package com.revature.project2.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class RentDetailDTO {
    private int rentId;
    private List<Integer> bookIds;
}
