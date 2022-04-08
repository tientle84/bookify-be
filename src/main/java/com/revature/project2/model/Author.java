package com.revature.project2.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="author_id")
    private int author_id;

    @Column(name="author_first_name")
    private String firstname;

    @Column(name="author_lastname")
    private String last_name;

    @Column(name="dob")
    private Timestamp dob;

    @Column(name="bio")
    private String bio;

    @Column(name="top_work")
    private String top_work;

    @Column(name="Image_url")
    private String Image_url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return author_id == author.author_id && Objects.equals(firstname, author.firstname) && Objects.equals(last_name, author.last_name) && Objects.equals(dob, author.dob) && Objects.equals(bio, author.bio) && Objects.equals(top_work, author.top_work) && Objects.equals(Image_url, author.Image_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author_id, firstname, last_name, dob, bio, top_work, Image_url);
    }
}
