package com.revature.project2.model;


import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="BookStatus")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BookStatus_id")
    private  int id;

    @Column(name = "book_status")
    private String book_status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStatus that = (BookStatus) o;
        return id == that.id && Objects.equals(book_status, that.book_status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book_status);
    }
}
