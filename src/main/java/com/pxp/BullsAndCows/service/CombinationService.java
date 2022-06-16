package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.repository.CombinationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CombinationService {

    private final CombinationRepository combinationRepository;

    public CombinationService(CombinationRepository combinationRepository) {
        this.combinationRepository = combinationRepository;
    }

    @Transactional
    public ResponseEntity addCombination(Combination combination) {
        if (combinationRepository.findMaxId() != null) {
            combination.setCombinationId(combinationRepository.findMaxId() + 1);
        }

        var newCombination = combinationRepository.save(combination);
        return ResponseEntity.ok(newCombination);
    }

    public List<Combination> readCombination() {
        return combinationRepository.findAll();
    }

    @Transactional
    public void deleteAllCombinations() {
        combinationRepository.deleteAll();
    }

    @Transactional
    public ResponseEntity deleteCombination(Long id) {
        combinationRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }
}
