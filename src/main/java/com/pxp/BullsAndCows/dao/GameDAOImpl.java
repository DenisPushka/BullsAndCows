package com.pxp.BullsAndCows.dao;

import com.pxp.BullsAndCows.entity.Game;

import java.util.Random;

public class GameDAOImpl implements GameDAO {

    public String CreateTrueCombination() {
        StringBuilder combination = new StringBuilder();
        var rnd = new Random();
        while (combination.length() != 4) {
            var r = rnd.nextInt(10);
            if (!combination.toString().contains(Integer.toString(r)))
                combination.append(r);
        }

        return combination.toString();
    }

    public String processing(String combination, Game game) {
        combination = combination.substring(13, 17);
        int b = 0, k = 0;
        var trueComb = game.getTrueComb();
        for (var i = 0; i < trueComb.length(); i++)
            if (trueComb.charAt(i) == combination.charAt(i))
                b++;
            else if (combination.indexOf(trueComb.charAt(i)) != -1)
                k++;

        return "\"" + b + "Б" + k + "К\"";
    }
}
