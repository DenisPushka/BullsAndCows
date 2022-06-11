package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final CombinationService combinationService;

    public GameService(GameRepository gameRepository, CombinationService combinationService) {
        this.gameRepository = gameRepository;
        this.combinationService = combinationService;
    }

    @Transactional
    public ResponseEntity addGame(Game game) {
        if (gameRepository.findMaxId() != null) {
            game.setGameId(gameRepository.findMaxId() + 1);
        }

        var newGame = gameRepository.save(game);
        return ResponseEntity.ok(newGame);
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public Game getGame(Long id){
        return gameRepository.findById(Math.toIntExact(id)).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public List<Game> addCombination(Long id){
        var combination = new Combination();
        combinationService.addCombination(combination);
        var game = getGame(id);
        game.getCombination().add(combination);
        return gameRepository.findAll();
    }

    @Transactional
    public ResponseEntity deleteGame(Long id) {
        gameRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteAllGames(){
        gameRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
