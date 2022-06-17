package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select max(g.gameId) from Game g")
    Long findMaxId();

    @Modifying
    @Query("DELETE FROM Game WHERE gameId = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query(
            value = "insert into game (game_id, idG, stop_watch, true_comb) values (:game_id, :idG, :stop_watch, :true_comb)",
            nativeQuery = true)
    void insertGame(@Param("game_id") Long game_id, @Param("idG") Long idG,
                    @Param("stop_watch") LocalDateTime stop_watch, @Param("true_comb") String true_comb);

    @Query(value = "SELECT g FROM Game g WHERE g.gameId IN :id")
    Game findGameById(@Param("id") Long id);
}
