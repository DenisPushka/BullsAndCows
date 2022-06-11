package com.pxp.BullsAndCows.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class Combination {

    @Id
    private int combinationId;

    private String combStep;

    private Date timeOfGame;

 /*   @OneToOne
    @JoinColumn(name = "userId")
    private User userId;*/

    public int getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(int combinationId) {
        this.combinationId = combinationId;
    }

    public String getCombStep() {
        return combStep;
    }

    public void setCombStep(String combStep) {
        this.combStep = combStep;
    }

    public Date getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(Date timeOfGame) {
        this.timeOfGame = timeOfGame;
    }

    @Override
    public String toString() {
        return "Combination{" +
                "combinationId=" + combinationId +
                ", combStep='" + combStep + '\'' +
                ", timeOfGame=" + timeOfGame +
                // ", userId=" + userId +
                '}';
    }
}
