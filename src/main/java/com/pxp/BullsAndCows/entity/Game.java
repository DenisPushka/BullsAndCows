package com.pxp.BullsAndCows.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class Game {

    @Id
    private int gameId;

    private String trueComb;

    @OneToMany
    @JoinColumn(name = "combinationId")
    private List<Combination> combination = new ArrayList<>();

    public Game() {
        trueComb = CreateTrueCombination();
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTrueComb() {
        return trueComb;
    }

    public void setTrueComb(String trueComb) {
        this.trueComb = trueComb;
    }

    public List<Combination> getCombination() {
        return combination;
    }

    public void setCombination(List<Combination> combination) {
        this.combination = combination;
    }

    private String CreateTrueCombination() {
        var combination = "";
        var rnd = new Random();
        while (combination.length() != 4)
            combination += rnd.nextInt(10);

        return combination;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId=" + gameId +
                ", trueComb='" + trueComb + '\'' +
                ", combination=" + combination +
                '}';
    }
}
