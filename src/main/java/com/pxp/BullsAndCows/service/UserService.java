package com.pxp.BullsAndCows.service;

import com.pxp.BullsAndCows.dao.UserDAOImpl;
import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.repository.UserRepository;
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
        if (userRepository.existsByNickname(user.getNickname())) {
           return userRepository.findByNickname(user.getNickname());
        } else if (userRepository.findAll().size() == 0) {
            user.setId(0);
           return userRepository.save(user);
        }

        user.setId(userRepository.findMaxId() + 1);
        return userRepository.save(user);
    }

    @Transactional
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User get(Long id) {
        return userRepository.findByUserId(id);
    }

    @Transactional
    public Game addGame(Long id) {
        var game = new Game();
        game.setIdG(get(id).getGames().size());
        gameService.addGame(game);
        var user = get(id);
        user.getGames().add(game);
        updateUser(id, user);
        return game;
    }

    @Transactional
    public User updateUser(Long id, User user) {
        var currentUser = userRepository.findById(id).orElseThrow(RuntimeException::new);
        currentUser.setNickname(user.getNickname());
        currentUser.setGames(user.getGames());
        return userRepository.save(user);
    }

    @Transactional
    public List<User> deleteUser(Long id) {
        var game = get(id).getGames();
        for (Game g : game)
            gameService.deleteGame(g.getGameId());
        userRepository.save(get(id));
        userRepository.deleteById(id);

        return userRepository.findAll();
    }

    @Transactional
    public String averageOfTime(Long id) {
        return new UserDAOImpl().averageCombTime(get(id));
    }
}
