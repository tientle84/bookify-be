package com.revature.project2.dao;

import com.revature.project2.model.Rent;
import com.revature.project2.model.RentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentDetailRepository extends JpaRepository<RentDetail, Integer> {

    @Query("SELECT r FROM RentDetail r JOIN r.rent rt WHERE rt.id = ?1")
    public abstract List<RentDetail> findAllByRentId(int rentId);
}
