package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity addGame(@RequestBody Game game) {
        return gameService.addGame(game);
    }

    @PostMapping( "addCombination/{id}")
    public String addCombination(@PathVariable Long id,@RequestBody String combination) {return gameService.addCombination(id, combination);}

    @GetMapping
    public List<Game> getGames() {
        return gameService.getGames();
    }

    @GetMapping("/getComb/{id}")
    public List<Combination> getCombinations(@PathVariable Long id) {
        return gameService.getCombinations(id);
    }

    @GetMapping("/{id}")
    public Game getLastGame(@PathVariable Long id){return gameService.getGame(id);}

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGame(@PathVariable Long id) {
        return gameService.deleteGame(id);
    }

    @DeleteMapping("/deleteAllGames")
    public ResponseEntity deleteAllGames() {
        return gameService.deleteAllGames();
    }
}
