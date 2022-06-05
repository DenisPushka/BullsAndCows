package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @RequestMapping(value = "infoGame", method = RequestMethod.GET)
    public String info() {
        return "The application is up infoGame...";
    }

    @RequestMapping(value = "createGame", method = RequestMethod.POST)
    public String createGame(@RequestBody Game game){
        return gameService.createGame(game);
    }

    @RequestMapping(value = "readGames", method = RequestMethod.GET)
    public List<Game> readGames(){
        return gameService.readGames();
    }

    @RequestMapping(value = "deleteGame", method = RequestMethod.DELETE)
    public String deleteGame(@RequestBody Game game){
        return gameService.deleteGame(game);
    }
}
