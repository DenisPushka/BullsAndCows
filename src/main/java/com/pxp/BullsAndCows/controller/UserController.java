package com.pxp.BullsAndCows.controller;

import com.pxp.BullsAndCows.entity.User;
import com.pxp.BullsAndCows.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String info() {
        return "The application is up...";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "readUsers", method = RequestMethod.GET)
    public List<User> readUsers() {
        return userService.readUser();
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.PUT)
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "addGame", method = RequestMethod.POST)
    public void addGame(@RequestBody User user) {
        userService.createGame(user);
    }

    @RequestMapping(value = "deleteUser", method = RequestMethod.DELETE)
    public String deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }
}
