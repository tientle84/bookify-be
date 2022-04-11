package com.revature.project2.dao;

import com.revature.project2.model.Book;
import com.revature.project2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface BookRepository extends JpaRepository<Book,Integer> {

        public abstract List<Book> findAll();


    }

