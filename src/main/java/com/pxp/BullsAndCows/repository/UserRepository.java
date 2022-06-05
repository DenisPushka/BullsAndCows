package com.pxp.BullsAndCows.repository;

import com.pxp.BullsAndCows.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByNickname(String nickname);

    List<User> findByNickname(String nickname);

    @Query("select max(us.userId) from User us")
    Integer findMaxId();
}