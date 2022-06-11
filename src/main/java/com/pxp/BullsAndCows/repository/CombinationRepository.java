package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Combination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Integer> {

    @Query("select max(c.combinationId) from Combination c")
    Integer findMaxId();
}
