package com.revature.project2.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "book")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int book_id;

    @Column(name="isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="publisher")
    private String publisher;

    @Column(name="publish_date")
    private Timestamp publish_date;

    @Column(name="image_url")
    private  String image_url;

    @Column(name="genre")
    private String genre;

   // @JoinColumn(name="book_status")
    @ManyToOne
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private BookStatus bookStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return book_id == book.book_id && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && Objects.equals(publish_date, book.publish_date) && Objects.equals(image_url, book.image_url) && Objects.equals(genre, book.genre) && Objects.equals(bookStatus, book.bookStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book_id, isbn, title, author, publisher, publish_date, image_url, genre, bookStatus);
    }
}
