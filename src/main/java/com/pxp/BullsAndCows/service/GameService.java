package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional
    public String createGame(Game game) {
        try {
            gameRepository.save(game);
            return "Game record created successfully.";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Game> readGames() {
        return gameRepository.findAll();
    }

    @Transactional
    public String deleteGame(Game game) {
        try {
            var games = gameRepository.findAll();
            games.stream().forEach(s -> {
                if (game.getGameId() == s.getGameId())
                    gameRepository.delete(s);
            });
            return "Game record deleted successfully.";
        } catch (Exception e) {
            throw e;
        }
    }
}
