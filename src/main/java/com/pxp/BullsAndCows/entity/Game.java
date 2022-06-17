package com.pxp.BullsAndCows.entity;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Audited
@AuditTable("T_GAME_AUDIT")
public class Game {

    @Id
    private long gameId;

    private String trueComb;

    private long idG;

    private LocalDateTime stopWatch;

    @AuditJoinTable(name = "T_GAME_COMBINATION_AUDIT",
            inverseJoinColumns = @JoinColumn(name = "combination_id"))
    @OneToMany(cascade = CascadeType.ALL)
    private List<Combination> combination = new ArrayList<>();

    public long getIdG() {
        return idG;
    }

    public void setIdG(long idG) {
        this.idG = idG;
    }

    public LocalDateTime getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(LocalDateTime stopWatch) {
        this.stopWatch = stopWatch;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
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
        stopWatch = LocalDateTime.now();
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
