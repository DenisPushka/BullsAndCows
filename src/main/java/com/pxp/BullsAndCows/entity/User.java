package com.pxp.BullsAndCows.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private int userId;

    private String nickname;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Game> games = new ArrayList<>();

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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> gameId) {
        this.games = gameId;
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
