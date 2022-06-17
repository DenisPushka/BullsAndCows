package com.pxp.BullsAndCows.entity;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Audited
@AuditTable("T_COMBINATION_AUDIT")
public class Combination {

    @Id
    private long combinationId;

    private String combStep;

    private LocalDateTime timeOfGame;

    public long getCombinationId() {
        return combinationId;
    }

    public void setCombinationId(long combinationId) {
        this.combinationId = combinationId;
    }

    public String getCombStep() {
        return combStep;
    }

    public void setCombStep(String combStep) {
        this.combStep = combStep;
    }

    public LocalDateTime getTimeOfGame() {
        return timeOfGame;
    }

    public void setTimeOfGame(LocalDateTime timeOfGame) {
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
