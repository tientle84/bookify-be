package com.revature.project2.dao;

import com.revature.project2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface BookRepository extends JpaRepository<Book,Integer> {

        public abstract List<Book> findAll();
        public abstract Book findById(int id);




    }

