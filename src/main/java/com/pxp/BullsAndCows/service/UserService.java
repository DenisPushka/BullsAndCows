package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final GameService gameService;

    public UserService(UserRepository userRepository, GameService gameService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
    }

    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByNickname(user.getNickname()))
            return user;

        user.setId(userRepository.findMaxId() + 1);
        var newUser = userRepository.save(user);
        return newUser;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(Math.toIntExact(id)).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public ResponseEntity addGame(Long id) {
        var game = new Game();
        gameService.addGame(game);
        var user = get(id);
        user.getGames().add(game);
        // updateUser(id, user);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity updateUser(Long id, User user) {
        var currentUser = userRepository.findById(Math.toIntExact(id)).orElseThrow(RuntimeException::new);
        currentUser.setNickname(user.getNickname());
        currentUser.setGames(user.getGames());
        currentUser = userRepository.save(user);

        return ResponseEntity.ok(currentUser);
    }

    @Transactional
    public ResponseEntity deleteUser(Long id) {
        var game = get(id).getGames();
        for (var i = 0; i < game.size(); i++) {
            var g = game.get(i);
            gameService.deleteGame((long) g.getGameId());
        }
        var u = get(id);
        userRepository.save(get(id));
        var u1 = get(id);
        userRepository.deleteById(Math.toIntExact(id));
        return ResponseEntity.ok().build();
    }
}
