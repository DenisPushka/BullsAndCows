package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.repository.CombinationRepository;
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
    public Combination addCombination(Combination combination) {
        if (combinationRepository.findMaxId() != null)
            combination.setCombinationId(combinationRepository.findMaxId() + 1);

        combinationRepository.insertCombination(combination.getCombinationId(), combination.getCombStep(), combination.getTimeOfGame());
        return combinationRepository.findCombinationById(combination.getCombinationId());
    }

    public List<Combination> readCombination() {
        return combinationRepository.findAll();
    }

    @Transactional
    public void deleteAllCombinations() {
        combinationRepository.deleteAll();
    }

    @Transactional
    public List<Combination> deleteCombination(Long id) {
        combinationRepository.deleteById(id);
        return combinationRepository.findAll();
    }
}
