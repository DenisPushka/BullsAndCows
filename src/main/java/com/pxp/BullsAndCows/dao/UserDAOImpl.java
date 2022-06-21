package com.pxp.BullsAndCows.dao;

import com.pxp.BullsAndCows.entity.User;

public class UserDAOImpl implements UserDAO {

    public String averageCombTime(User user) {
        var games = user.getGames();
        if (games.size() == 0)
            return "\"00:00:00\"";

        long seconds = 0;
        for (var game: games) {
            var max = game.getCombination().size() - 1;
            var hhmmss = game.getCombination().get(max).getTimeOfGame();
            seconds += hhmmss.getHour() * 60 * 60;
            seconds += hhmmss.getMinute() * 60;
            seconds += hhmmss.getSecond();
        }

        seconds /= games.size();

        long hh = seconds / 60 / 60;
        long mm = (seconds / 60) % 60;
        long ss = seconds % 60;
        return String.format("\"%02d:%02d:%02d\"", hh,mm,ss);
    }
}
