package com.marketplace.controller;

import com.marketplace.model.User;
import com.marketplace.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<User> listUsers = userService.allUsers();
        for (User u : listUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                return "signup_form";
            }
        }
        if (!listUsers.contains(user)) {
            userService.createUser(user);
            return "register_success";
        }
        return "signup_form";
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("signup_form");
    }
}
