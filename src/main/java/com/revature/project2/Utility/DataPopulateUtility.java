package com.revature.project2.Utility;

import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.model.User;
import com.revature.project2.model.UserRole;
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
        user1.setPassword("password");
        user1.setFirstName("jenob");
        user1.setLastName("job");
        user1.setAddress("Toronto");
        user1.setPhone("2232443434");
        user1.setRole(manager);
        em.persist(user1);

        User user2 = new User();
        user2.setEmail("tienle@gmail.com");
        user2.setPassword("123456");
        user2.setFirstName("Tien");
        user2.setLastName("Le");
        user2.setAddress("123 abc");
        user2.setPhone("4074567890");
        user2.setRole(manager);
        em.persist(user2);

        User user3 = new User();
        user3.setEmail("johndoe@gmail.com");
        user3.setPassword("123456");
        user3.setFirstName("John");
        user3.setLastName("Doe");
        user3.setAddress("123 abc");
        user3.setPhone("5089877890");
        user3.setRole(renter);
        em.persist(user3);

        /*
        BOOK STATUS
         */
        BookStatus bookStatus1=new BookStatus();
        bookStatus1.setBook_status("Available");
        em.persist(bookStatus1);

        BookStatus bookStatus2=new BookStatus();
        bookStatus2.setBook_status("Not Available");
        em.persist(bookStatus2);

        /*
        * ----------------Books----------
        * */
        Book book = new Book();
        book.setAuthor("Robin Sharma");
        book.setGenre("self Help");
        book.setIsbn("978-3-16-148410-0");
        book.setPublishDate(String.valueOf((String.valueOf(LocalDate.of(2018,12,04)))));
        book.setImageUrl("https://www.robinsharma.com/files/img/book/9156802291265ef1ee1f6720f3ab8bc0.png");
        book.setPublisher("HapperCollins");
        book.setTitle("The 5 Am Club");
        //book.setStatus(bookStatus1);
        book.setStatus("Available");
        em.persist(book);

        Book book1 = new Book();
        book1.setAuthor("Karen Swan");
        book1.setGenre("Romance novel");
        book1.setIsbn("978-1-50-984062-5");
        book1.setPublishDate(String.valueOf((String.valueOf(LocalDate.of(2018,04,24)))));
        book1.setImageUrl("https://www.robinsharma.com/files/img/book/9156802291265ef1ee1f6720f3ab8bc0.png");
        book1.setPublisher("Pan Macmillan");
        book1.setTitle("The Greek Escape");
        //book1.setBookStatus(bookStatus2);
        book.setStatus("Available");
        em.persist(book1);

    }

}
