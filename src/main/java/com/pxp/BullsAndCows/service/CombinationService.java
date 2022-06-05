package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Combination;
import com.pxp.BullsAndCows.repository.CombinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CombinationService {

    @Autowired
    private CombinationRepository combinationRepository;

    @Transactional
    public String createCombination(Combination combination) {
        try {
            combinationRepository.save(combination);
            return "Combination record created successfully.";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Combination> readCombination() {
        return combinationRepository.findAll();
    }

    @Transactional
    public void deleteCombination(Combination combination) {
        try {
            var combinations = combinationRepository.findAll();
            combinations.stream().forEach(s -> {
                if (combination.getCombinationId() == s.getCombinationId())
                    combinationRepository.delete(s);
            });
        } catch (Exception e) {
            throw e;
        }
    }
}
