package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.repository.UserRepository;
import com.pxp.BullsAndCows.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("game/{id}")
    public List<Game> getGames(@PathVariable Long id) {
        List<Game> games = new ArrayList<>();
        userRepository.
                findAll().
                forEach(user -> {
                    if (user.getId() == id)
                        games.addAll(user.getGames());
                });

        return games;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("addGame/{id}")
    public ResponseEntity addGame(@PathVariable Long id) {
        return userService.addGame(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
