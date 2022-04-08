package com.revature.project2.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="Publisher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publisher_id")
    private int id;
    @Column(name="publisher_name")
    private String publisher_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return id == publisher.id && Objects.equals(publisher_name, publisher.publisher_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publisher_name);
    }
}
