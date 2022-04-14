package com.revature.project2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="rent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @Column()
    private LocalDate date;

    @ManyToOne
    @JoinColumn()
    //@OnDelete(action= OnDeleteAction.CASCADE)
    private User manager;

    @ManyToOne
    @JoinColumn()
    //@OnDelete(action= OnDeleteAction.CASCADE)
    private User renter;

    public Rent(LocalDate date, User manager, User renter) {
        this.date = date;
        this.manager = manager;
        this.renter = renter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id && Objects.equals(date, rent.date) && Objects.equals(manager, rent.manager) && Objects.equals(renter, rent.renter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, manager, renter);
    }
}
