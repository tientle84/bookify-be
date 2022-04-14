package com.revature.project2.Utility;

import com.revature.project2.model.*;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
public class DataPopulateUtility {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void populateInitialData(){
        /*
        userRole
         */
        UserRole manager = new UserRole();
        manager.setRole("manager");
        em.persist(manager);

        UserRole renter = new UserRole();
        renter.setRole("renter");
        em.persist(renter);

        /*
        user
         */
        User user1 = new User();
        user1.setEmail("jenob@gmail.com");
        user1.setPassword("�\f!y=zc:��Y:�LKp");
        user1.setFirstName("jenob");
        user1.setLastName("job");
        user1.setAddress("Toronto");
        user1.setPhone("2232443434");
        user1.setRole(manager);
        em.persist(user1);

        User user2 = new User();
        user2.setEmail("tienle@gmail.com");
        user2.setPassword("�\f!y=zc:��Y:�LKp");
        user2.setFirstName("Tien");
        user2.setLastName("Le");
        user2.setAddress("123 abc");
        user2.setPhone("4074567890");
        user2.setRole(manager);
        em.persist(user2);

        User user3 = new User();
        user3.setEmail("johndoe@gmail.com");
        user3.setPassword("�\f!y=zc:��Y:�LKp");
        user3.setFirstName("John");
        user3.setLastName("Doe");
        user3.setAddress("123 abc");
        user3.setPhone("5089877890");
        user3.setRole(renter);
        em.persist(user3);

        User user4 = new User();
        user4.setEmail("janedoe@gmail.com");
        user4.setPassword("�\f!y=zc:��Y:�LKp");
        user4.setFirstName("Jane");
        user4.setLastName("Doe");
        user4.setAddress("1234 xyz");
        user4.setPhone("9998884561");
        user4.setRole(renter);
        em.persist(user4);

        /* BOOK STATUS */

        BookStatus bookStatus1 = new BookStatus();
        bookStatus1.setStatus("Available");
        em.persist(bookStatus1);

        BookStatus bookStatus2 = new BookStatus();
        bookStatus2.setStatus("Not Available");
        em.persist(bookStatus2);

        /* BOOK */

        Book book = new Book();
        book.setIsbn("0373761228");
        book.setTitle("Boss, The Beauty, And The Bargain");
        book.setAuthor("Judith McWilliams");
        book.setPublisher("Silhouette");
        book.setPublishDate("01/12/1997");
        book.setGenre("Fiction");
        book.setImageUrl("https://covers.openlibrary.org/b/id/220453-L.jpg");
        book.setStatus(bookStatus1);
        em.persist(book);

        Book book1 = new Book();
        book1.setIsbn("0262540290");
        book1.setTitle("Primer of visual literacy");
        book1.setAuthor("Donis A. Dondis");
        book1.setPublisher("MIT Press");
        book1.setPublishDate("1974");
        book1.setGenre("Technique");
        book1.setImageUrl("https://covers.openlibrary.org/b/id/6257679-L.jpg");
        book1.setStatus(bookStatus1);
        em.persist(book1);

        Book book2 = new Book();
        book2.setIsbn("0373198094");
        book2.setTitle("The Matchmaking Machine");
        book2.setAuthor("Judith McWilliams");
        book2.setPublisher("Silhouette");
        book2.setPublishDate("03/12/2006");
        book2.setGenre("Romance");
        book2.setImageUrl("https://covers.openlibrary.org/b/id/215776-L.jpg");
        book2.setStatus(bookStatus1);
        em.persist(book2);

        Book book3 = new Book();
        book3.setIsbn("0373761229");
        book3.setTitle("Your perfect right");
        book3.setAuthor("Robert E. Alberti");
        book3.setPublisher("Impact");
        book3.setPublishDate("1974");
        book3.setGenre("Communication");
        book3.setImageUrl("https://covers.openlibrary.org/b/id/9839075-L.jpg");
        book3.setStatus(bookStatus1);
        em.persist(book3);

        Book book4 = new Book();
        book4.setIsbn("0373761252");
        book4.setTitle("Lolita");
        book4.setAuthor("Vladimir Nabokov");
        book4.setPublisher("Caedmon");
        book4.setPublishDate("1981");
        book4.setGenre("Fiction");
        book4.setImageUrl("https://covers.openlibrary.org/b/id/10522912-L.jpg");
        book4.setStatus(bookStatus2);
        em.persist(book4);

        /* RENT */

        Rent rent1 = new Rent();
        rent1.setDate(LocalDate.now());
        rent1.setManager(user1);
        rent1.setRenter(user3);
        em.persist(rent1);

        Rent rent2 = new Rent();
        rent2.setDate(LocalDate.now());
        rent2.setManager(user1);
        rent2.setRenter(user4);
        em.persist(rent2);
    }
}
