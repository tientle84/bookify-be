package com.revature.project2.model;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

//@Entity
//@Table(name = "bookReview")
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class Bookreview {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="bookReview_id")
//    private int bookReview_id;
//
//    //@JoinColumn(name="book_id")
//    @ManyToOne
//    //@OnDelete(action= OnDeleteAction.CASCADE)
//    private  Book book_id;
//
//    @Column(name="rating")
//    private int rating;
//
//    //@JoinColumn(name = "user_id")
//    @ManyToOne
//   // @OnDelete(action= OnDeleteAction.CASCADE)
//    private User user_id;
//
//    @Column(name="feedback")
//    private String feedback;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Bookreview that = (Bookreview) o;
//        return bookReview_id == that.bookReview_id && rating == that.rating && Objects.equals(book_id, that.book_id) && Objects.equals(user_id, that.user_id) && Objects.equals(feedback, that.feedback);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(bookReview_id, book_id, rating, user_id, feedback);
//    }
//
}
