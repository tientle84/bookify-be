//package com.revature.project2.model;
//
//import lombok.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.Objects;
//
//@Entity
//@Table(name="rent")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Rent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "rent_id")
//    private int rent_id;
//
//    //@JoinColumn(name="render_id")
//    @ManyToOne
//    //@OnDelete(action= OnDeleteAction.CASCADE)
//    private User renter_id;
//
//   // @JoinColumn(name="liberian_id")
//    @ManyToOne
//    //@OnDelete(action= OnDeleteAction.CASCADE)
//    private User liberian_id;
//
//    @Column(name = "date")
//    private Date date;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Rent rent = (Rent) o;
//        return rent_id == rent.rent_id && Objects.equals(renter_id, rent.renter_id) && Objects.equals(liberian_id, rent.liberian_id) && Objects.equals(date, rent.date);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(rent_id, renter_id, liberian_id, date);
//    }
//}
