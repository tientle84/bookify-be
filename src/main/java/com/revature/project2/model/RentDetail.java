package com.revature.project2.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="rent_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @ManyToOne
    @JoinColumn()
    // @OnDelete(action= OnDeleteAction.CASCADE)
    private Rent rent;

    @ManyToOne
    @JoinColumn()
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name="return_date")
    private LocalDate returnDate;

    @Column(name="fine_amount")
    private double fineAmount;

    public RentDetail(Rent rent, Book book, LocalDate expiryDate, LocalDate returnDate, double fineAmount) {
        this.rent = rent;
        this.book = book;
        this.expiryDate = expiryDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentDetail that = (RentDetail) o;
        return id == that.id && Double.compare(that.fineAmount, fineAmount) == 0 && Objects.equals(rent, that.rent) && Objects.equals(book, that.book) && Objects.equals(expiryDate, that.expiryDate) && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rent, book, expiryDate, returnDate, fineAmount);
    }
}
