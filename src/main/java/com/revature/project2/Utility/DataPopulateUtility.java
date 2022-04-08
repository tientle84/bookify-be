package com.revature.project2.Utility;

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
        /*
        userRole
         */
        UserRole liberian=new UserRole();
        liberian.setUserRole("liberian");
        em.persist(liberian);

        UserRole bookRenter=new UserRole();
        bookRenter.setUserRole("bookRenter");
        em.persist(bookRenter);

        /*
        user
         */
        User user1=new User();
        user1.setEmailId("jenob@gmail.com");
        user1.setAddress("Toronto");
        user1.setPassword("password");
        user1.setFirst_name("jenob");
        user1.setLast_name("job");
        user1.setPhone("2232443434");
        user1.setUserRole(liberian);
        em.persist(user1);

        User user2=new User();
        user2.setEmailId("jinu@gmail.com");
        user2.setAddress("Toronto");
        user2.setPassword("password");
        user2.setFirst_name("jinu");
        user2.setLast_name("job");
        user2.setPhone("34234234234");
        user2.setUserRole(bookRenter);
        em.persist(user2);





    }

}
