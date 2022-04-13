//package com.revature.project2.model;
//
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Objects;
//
//@Entity
//@Table(name="RentDetails")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class RentDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="rentDetails_Id")
//    private int rentDetails_id;
//
//    //@JoinColumn(name = "rent_id")
//    @OneToOne
//   // @OnDelete(action= OnDeleteAction.CASCADE)
//    private Rent rent_id;
//
//    //@JoinColumn(name = "book_id")
//    @OneToOne
//    //@OnDelete(action = OnDeleteAction.CASCADE)
//    private Book book_id;
//
//    @Column(name = "expiry_date")
//    private String expiry_date;
//
//    @Column(name="return_date")
//    private String return_date;
//
//    @Column(name="final_amount")
//    private int final_amount;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        RentDetails that = (RentDetails) o;
//        return rentDetails_id == that.rentDetails_id && final_amount == that.final_amount && Objects.equals(rent_id, that.rent_id) && Objects.equals(book_id, that.book_id) && Objects.equals(expiry_date, that.expiry_date) && Objects.equals(return_date, that.return_date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(rentDetails_id, rent_id, book_id, expiry_date, return_date, final_amount);
//    }
//}
