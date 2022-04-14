package com.revature.project2.dao;

import com.revature.project2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    public abstract User findByEmailAndPassword(String email, String password);

}
