package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.service.CombinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CombinationController {
    @Autowired
    private CombinationService combinationService;

    @RequestMapping(value = "infoCombination", method = RequestMethod.GET)
    public String info() {
        return "The application is up infoCombination ...";
    }

    @RequestMapping(value = "addCombination", method = RequestMethod.POST)
    public String createGame(@RequestBody Combination combination){
        return combinationService.createCombination(combination);
    }

    @RequestMapping(value = "readCombinations", method = RequestMethod.GET)
    public List<Combination> readGames(){
        return combinationService.readCombination();
    }

    @RequestMapping(value = "deleteCombination", method = RequestMethod.DELETE)
    public void deleteGame(@RequestBody Combination combination){
        combinationService.deleteCombination(combination);
    }
}
