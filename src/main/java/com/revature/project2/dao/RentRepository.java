package com.revature.project2.dao;

import com.revature.project2.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent,Integer> {

    @Query("SELECT r FROM Rent r JOIN r.renter rd WHERE rd.id = ?1")
    public abstract List<Rent> findAllByRenterId(int rentId);
}
