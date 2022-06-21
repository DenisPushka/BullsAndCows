package com.pxp.BullsAndCows.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@RequiredArgsConstructor
@Audited
@AuditTable("t_user_audit")
public class User {

    @Column(columnDefinition = "created_date", nullable  = false, updatable = false)
    @CreatedDate
    private long createDate; // = new Date().getTime();//LocalDateTime.now().getLong(ChronoField.MINUTE_OF_DAY);

//    @Column(columnDefinition = "modified_date")
//    @LastModifiedDate
//    private long modifiedDate;
//
//    @Column(columnDefinition = "created_by")
//    @CreatedBy
//    private String createdBy;
//
//    @Column(columnDefinition = "modified_by")
//    @LastModifiedBy
//    private String modifiedBy;

    @Id
    private long userId;

    private String nickname;

    public User(String nickname) {
        this.nickname = nickname;
    }

    //    @AuditJoinTable(name = "T_USER_GAME_AUDIT",
//        inverseJoinColumns = @JoinColumn(name = "game_id"))
    @OneToMany(cascade = CascadeType.ALL)
    @NotAudited
    private List<Game> games = new ArrayList<>();

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", nickname='" + nickname +
                ", games=" + games.size() +
                '}';
    }
}
