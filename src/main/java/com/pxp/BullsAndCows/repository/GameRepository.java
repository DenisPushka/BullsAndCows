package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

}
