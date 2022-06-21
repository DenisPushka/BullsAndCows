package com.pxp.BullsAndCows.dao;

import com.pxp.BullsAndCows.entity.Game;

public interface GameDAO {
    String CreateTrueCombination();

    String processing(String combination, Game game);
}
