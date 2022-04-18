package com.revature.project2.Utility;

import com.revature.project2.model.Book;
import com.revature.project2.model.BookStatus;
import com.revature.project2.model.User;
import com.revature.project2.model.UserRole;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
public class DataPopulateUtility {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void populateInitialData(){

        String pw = "�\f!y=zc:��Y:�LKp";
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
        user1.setPassword(pw);
        user1.setFirstName("jenob");
        user1.setLastName("job");
        user1.setAddress("Toronto");
        user1.setPhone("2232443434");
        user1.setRole(manager);
        em.persist(user1);

        User user2 = new User();
        user2.setEmail("tienle@gmail.com");
        user2.setPassword(pw);
        user2.setFirstName("Tien");
        user2.setLastName("Le");
        user2.setAddress("123 abc");
        user2.setPhone("4074567890");
        user2.setRole(manager);
        em.persist(user2);

        User user3 = new User();
        user3.setEmail("johndoe@gmail.com");
        user3.setPassword(pw);
        user3.setFirstName("John");
        user3.setLastName("Doe");
        user3.setAddress("123 abc");
        user3.setPhone("5089877890");
        user3.setRole(renter);
        em.persist(user3);

        User user4 = new User();
        user4.setEmail("janedoe@gmail.com");
        user4.setPassword(pw);
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

        Book book1 = new Book("0373761228", "Boss, The Beauty, And The Bargain", "Judith McWilliams", "Silhouette", "01/12/1997", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/220453-L.jpg");
        Book book2 = new Book("0262540290", "Primer of visual literacy", "Donis A. Dondis", "MIT Press", "1974", "Technique", bookStatus1, "https://covers.openlibrary.org/b/id/6257679-L.jpg");
        Book book3 = new Book("0373198094", "The Matchmaking Machine", "Judith McWilliams", "Silhouette", "03/12/2006", "Romance", bookStatus1, "https://covers.openlibrary.org/b/id/215776-L.jpg");
        Book book4 = new Book("0373761229", "Your perfect right", "Robert E. Alberti", "Impact", "1974", "Communication", bookStatus1, "https://covers.openlibrary.org/b/id/9839075-L.jpg");
        Book book5 = new Book("0373761252", "Lolita", "Vladimir Nabokov", "Caedmon", "1981", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/10522912-L.jpg");
        Book book6 = new Book("0397317832", "Deadwood City", "Edward Packard", "Bantam Books", "1978", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/12703853-M.jpg");
        Book book7 = new Book("0340893656", "Naughtiest Girl Collection", "Enid Blyton", "Hodder Children's Books", "10/14/2004", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/2403588-M.jpg");
        Book book8 = new Book("0063038366", "Not Here to Be Liked", "Michelle Quach", "Katherine Tegen Books", "10/19/2021", "Children's fiction", bookStatus1, "https://covers.openlibrary.org/b/id/12317857-M.jpg");
        Book book9 = new Book("0747542988", "Harry Potter and the Philosopher's Stone", "J. K. Rowling", "Bloomsbury", "1998", "Juvenile fiction", bookStatus1, "https://covers.openlibrary.org/b/id/10521270-M.jpg");
        Book book10 = new Book("0380800829", "The Duke and I", "Julia Quinn", "Avon Books, Inc.", "2000", "Romance", bookStatus1, "https://covers.openlibrary.org/b/id/10548994-M.jpg");
        Book book11 = new Book("8498381452", "Harry Potter y las Reliquias de la Muerte", "J. K. Rowling", "Salamandra", "02/2008", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/8259297-M.jpg");
        Book book12 = new Book("0974571725", "You Were Born Rich", "Napoleon Hill", "www.AsAManThinkith.net", "2003", "Finance", bookStatus1, "https://covers.openlibrary.org/b/id/8575723-M.jpg");
        Book book13 = new Book("0439136350", "Harry Potter and the Prisoner of Azkaban", "J. K. Rowling", "Arthur A. Levine Books", "2007", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/12025581-M.jpg");
        Book book14 = new Book("0810994550", "Diary of a Wimpy Kid", "Jeff Kinney", "Amulet Books", "2007", "Fiction", bookStatus1, "https://covers.openlibrary.org/b/id/6969303-M.jpg");
        Book book15 = new Book("0671772465", "How To Win Friends and Influence People", "Dale Carnegie", "Pocket", "06/20/1970", "Business", bookStatus1, "https://covers.openlibrary.org/b/id/8231858-M.jpg");

        em.persist(book1);  em.persist(book2);  em.persist(book3);  em.persist(book4);  em.persist(book5);
        em.persist(book6);  em.persist(book7);  em.persist(book8);  em.persist(book9);  em.persist(book10);
        em.persist(book11); em.persist(book12); em.persist(book13); em.persist(book14); em.persist(book15);
    }
}
