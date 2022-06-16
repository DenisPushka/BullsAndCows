package com.pxp.BullsAndCows.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class Game {

    @Id
    private int gameId;

    private String trueComb;

    private int idG;

    private Date stopWatch;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Combination> combination = new ArrayList<>();

    public int getIdG() {
        return idG;
    }

    public void setIdG(int idG) {
        this.idG = idG;
    }

    public Date getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(Date stopWatch) {
        this.stopWatch = stopWatch;
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

    public Game() {
        stopWatch = new Date();
        trueComb = CreateTrueCombination();
    }

    private String CreateTrueCombination() {
        StringBuilder combination = new StringBuilder();
        var rnd = new Random();
        while (combination.length() != 4) {
            var r = rnd.nextInt(10);
            if (!combination.toString().contains(Integer.toString(r)))
                combination.append(r);
        }

        return combination.toString();
    }

    public String processing(String combination) {
        combination = combination.substring(13, 17);
        int b = 0, k = 0;
        for (var i = 0; i < trueComb.length(); i++)
            if (trueComb.charAt(i) == combination.charAt(i))
                b++;
            else if (combination.indexOf(trueComb.charAt(i)) != -1)
                k++;

        return "\"" + b + "Б" + k + "К\"";
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
