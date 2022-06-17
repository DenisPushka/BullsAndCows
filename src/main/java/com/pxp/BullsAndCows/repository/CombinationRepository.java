package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {

    @Query("select max(c.combinationId) from Combination c")
    Long findMaxId();

    @Modifying
    @Query("DELETE FROM Combination WHERE combinationId = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query(
            value = "insert into combination (combination_id, comb_step, time_of_game) values (:combination_id, :comb_step, :time_of_game)",
            nativeQuery = true)
    void insertCombination(@Param("combination_id") Long combination_id, @Param("comb_step") String comb_step,
                    @Param("time_of_game") LocalDateTime time_of_game);

    @Query(value = "SELECT c FROM Combination c WHERE c.combinationId IN :id")
    Combination findCombinationById(@Param("id") Long id);
}