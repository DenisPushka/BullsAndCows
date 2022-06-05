package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Integer> {

    //boolean existsByCombinationFromUser(User user);

    @Query("select max(c.combinationId) from Combination c")
    Integer findMaxId();
}
