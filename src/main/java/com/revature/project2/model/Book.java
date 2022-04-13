package com.revature.project2.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
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
    private int id;

    @Column(name="isbn", unique = true)
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="publisher")
    private String publisher;

    @Column(name="publish_date")
    private String publishDate;

    @Column(name="genre")
    private String genre;

    //@Column(name="status", columnDefinition = "varchar(50) default 'Available'", insertable = false)
    //@ColumnDefault("Available")
    @ManyToOne
    @JoinColumn(name = "status_id")
    private BookStatus status;

    @Column(name="image_url")
    private String imageUrl;

    public Book(String isbn, String title, String author, String publisher, String publishDate, String genre, BookStatus status, String imageUrl) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.genre = genre;
        this.status = status;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && Objects.equals(publishDate, book.publishDate) && Objects.equals(genre, book.genre) && Objects.equals(status, book.status) && Objects.equals(imageUrl, book.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, publisher, publishDate, genre, status, imageUrl);
    }
}
