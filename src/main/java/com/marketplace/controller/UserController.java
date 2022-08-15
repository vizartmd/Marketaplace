package com.marketplace.controller;

import com.marketplace.model.User;
import com.marketplace.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/users")
    public ModelAndView listUsers(HttpServletRequest request, Model model) {
        return userService.allUsersOnPage(request, model);
    }

}
