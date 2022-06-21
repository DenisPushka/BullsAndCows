package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.dao.GameDAOImpl;
import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.repository.GameRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    public Game addGame(Game game) {
        if (gameRepository.findMaxId() != null)
            game.setGameId(gameRepository.findMaxId() + 1);

        gameRepository.insertGame(game.getGameId(), game.getIdG(), game.getStopWatch(), game.getTrueComb());
        return gameRepository.findGameById(game.getGameId());
    }

    public List<Game> getGames() {
        return gameRepository.findAll();
    }

    public List<Combination> getCombinations(Long id) {
        return getGame(id).getCombination();
    }

    public Game getGame(Long id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public String addCombination(Long id, String comb) {
        var combination = new Combination();
        var game = getGame(id);
        combination.setCombStep(comb);
        combination.setTimeOfGame(getTime(game));
        combinationService.addCombination(combination);
        game.getCombination().add(combination);
        updateGame(id, game);

        return new GameDAOImpl().processing(comb,game);
    }

    private LocalDateTime getTime(Game game) {
        var ldt = game.getStopWatch();
        var zdt = ldt.atZone(ZoneId.of("+05:00"));
        long startTimeInMillis = zdt.toInstant().toEpochMilli();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTimeInMillis = System.currentTimeMillis();
        long secondAfterStart = (endTimeInMillis - startTimeInMillis) / 1000;

        var hour = (int) secondAfterStart / 3600;
        var min = (int) secondAfterStart / 60 % 60;
        var sec = (int) secondAfterStart / 1 % 60;

        var ld = LocalDate.now();
        return ld.atTime(hour, min, sec);
    }

    @Transactional
    public Game updateGame(Long id, Game game) {
        var currentGame = gameRepository.findById(id).orElseThrow(RuntimeException::new);
        currentGame.setTrueComb(game.getTrueComb());
        currentGame.setCombination(game.getCombination());
        gameRepository.save(currentGame);

        return currentGame;
    }

    @Transactional
    public List<Game> deleteGame(Long id) {
        var com = getGame(id).getCombination();
        for (var c : com)
            combinationService.deleteCombination(c.getCombinationId());
        gameRepository.deleteById(id);
        return gameRepository.findAll();
    }

    @Transactional
    public List<Game> deleteAllGames() {
        gameRepository.deleteAll();
        return gameRepository.findAll();
    }
}
