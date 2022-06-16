package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.repository.GameRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    public List<Combination> getCombinations(Long id) {
        return getGame(id).getCombination();
    }

    public Game getGame(Long id) {
        return gameRepository.findById(Math.toIntExact(id)).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public String addCombination(Long id, String comb) {
        var combination = new Combination();
        var game = getGame(id);
        combination.setCombStep(comb);
        var a = getTime(game);
        combination.setTimeOfGame(a);
        combinationService.addCombination(combination);
        game.getCombination().add(combination);
        updateGame(id, game);

        return game.processing(comb);
    }

    private String getTime(Game game) {
        var stopWatch = new Date();
        var hour = Integer.toString(Math.abs(stopWatch.getHours() - game.getStopWatch().getHours()));
        if (hour.length() == 1) hour = "0" + hour;
        var min = Integer.toString(Math.abs(stopWatch.getMinutes() - game.getStopWatch().getMinutes()));
        if (min.length() == 1) min = "0" + min;
        var sec = Integer.toString(Math.abs(stopWatch.getSeconds() - game.getStopWatch().getSeconds()));
        if (sec.length() == 1) sec = "0" + sec;
        return hour + ":" + min + ":" + sec;
    }

    @Transactional
    public ResponseEntity updateGame(Long id, Game game) {
        var currentGame = gameRepository.findById(Math.toIntExact(id)).orElseThrow(RuntimeException::new);
        currentGame.setTrueComb(game.getTrueComb());
        currentGame.setCombination(game.getCombination());
        currentGame = gameRepository.save(game);

        return ResponseEntity.ok(currentGame);
    }

    @Transactional
    public ResponseEntity deleteGame(Long id) {
        gameRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity deleteAllGames() {
        gameRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
