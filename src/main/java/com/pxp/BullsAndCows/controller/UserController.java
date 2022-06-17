package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.Game;
import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PostMapping("addGame/{id}")
    public Game addGame(@PathVariable Long id) {
        return userService.addGame(id);
    }

    @GetMapping("/AverageTime/{id}")
    public String averageTime(@PathVariable Long id){return userService.averageOfTime(id);}

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
