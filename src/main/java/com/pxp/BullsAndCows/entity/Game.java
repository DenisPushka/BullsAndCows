package com.pxp.BullsAndCows.entity;

import com.pxp.BullsAndCows.dao.GameDAO;
import com.pxp.BullsAndCows.dao.GameDAOImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
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
        trueComb = new GameDAOImpl().CreateTrueCombination(); // CreateTrueCombination();
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
