package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.repository.GameRepository;
import com.pxp.BullsAndCows.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameService gameService;

    @Transactional
    public String createUser(User user) {
        try {
            if (!userRepository.existsByNickname(user.getNickname())) {
                user.setId(null == userRepository.findMaxId() ? 0 : userRepository.findMaxId() + 1);
                userRepository.save(user);
                return "User record created successfully.";
            }

            return "User already exists in the database.";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<User> readUser() {
        return userRepository.findAll();
    }

    @Transactional
    public void createGame(User user) {
        try {
            var game = new Game();
            gameService.createGame(game);
            user.getGameId().add(game);
            updateUser(user);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public String updateUser(User user) {
        if (userRepository.existsByNickname(user.getNickname())) {
            try {
                var users = userRepository.findByNickname(user.getNickname());
                users.stream().forEach(s -> {
                    if (user.getNickname().equals(s.getNickname())) {
                        var userToUpdate = userRepository.findById(s.getId()).get();
                        userToUpdate.setNickname(user.getNickname());
                        userToUpdate.setGameId(user.getGameId());
                        userRepository.save(userToUpdate);
                    }
                });

                return "User record updated.";
            } catch (Exception e) {
                throw e;
            }
        }

        return "User does not exists in the database.";
    }

    @Transactional
    public String deleteUser(User user) {
        if (userRepository.existsByNickname(user.getNickname())) {
            try {
                var users = userRepository.findByNickname(user.getNickname());
                users.stream().forEach(s -> userRepository.delete(s));
                return "User record deleted successfully.";
            } catch (Exception e) {
                throw e;
            }
        }

        return "User does not exist";
    }
}
