package com.pxp.BullsAndCows.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    private int userId;

    private String nickname;

    @OneToMany
    @JoinColumn(name = "gameId")
    private List<Game> gameId = new ArrayList<>();

    public User(){}

    public int getId() {
        return userId;
    }

    public void setId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<Game> getGameId() {
        return gameId;
    }

    public void setGameId(List<Game> gameId) {
        this.gameId = gameId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", nickname='" + nickname + '\'' +
                ", gameId=" + gameId +
                '}';
    }
}
