package com.pxp.BullsAndCows.entity;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Audited
@AuditTable("T_USER_AUDIT")
public class User {
    @Id
    private long userId;

    private String nickname;

    @AuditJoinTable(name = "T_USER_GAME_AUDIT",
        inverseJoinColumns = @JoinColumn(name = "game_id"))
    @OneToMany(cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

    public User(){}

    public long getId() {
        return userId;
    }

    public void setId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> gameId) {
        this.games = gameId;
    }

    public String averageCombTime() {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", nickname='" + nickname + '\'' +
                ", games=" + games.size() +
                '}';
    }
}
