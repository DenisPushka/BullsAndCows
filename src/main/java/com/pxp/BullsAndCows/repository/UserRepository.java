package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByNickname(String nickname);

    User findByNickname(String nickname);

    User findByUserId(Long id);

    @Query("select max(us.userId) from User us")
    Long findMaxId();

    @Modifying
    @Query("DELETE FROM User WHERE userId = :id")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query(
            value = "insert into User (user_id, nickname) values (:user_id, :nickname)",
            nativeQuery = true)
    void insertUser(@Param("user_id") Long user_id, @Param("nickname") String nickname);

    @Query(value = "SELECT u FROM User u WHERE u.nickname IN :nickname")
    User findUserByName(@Param("nickname") String nickname);

    @Modifying
    @Query("update User u set u.games = :games where u.nickname = :nickname")
    void updateUser(@Param("games") Game[] games,
                                   @Param("nickname") String nickname);
}