package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.service.CombinationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/combination")
public class CombinationController {

    private final CombinationService combinationService;

    public CombinationController(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    @PostMapping
    public ResponseEntity addCombination(@RequestBody Combination combination) {
        return combinationService.addCombination(combination);
    }

    @GetMapping
    public List<Combination> readCombination() {
        return combinationService.readCombination();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        combinationService.deleteAllCombinations();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCombination(@PathVariable Long id) {
        return combinationService.deleteCombination(id);
    }
}
