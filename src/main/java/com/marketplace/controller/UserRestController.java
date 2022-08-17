package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/all-users")
    public List<User> listUsers() {
        return userService.allUsers();
    }

    @GetMapping("/add-to-my-products/{id}/email/{email}")
    public List<Product> addToMyProducts(@PathVariable Long id, @PathVariable String email) {
        return userService.addToMyProducts(id, email);
    }

    @GetMapping("/remove-from-my-products/{id}/email/{email}")
    public List<Product> removeFromMyProducts(@PathVariable Long id, @PathVariable String email) {
        return userService.removeFromMyProducts(id, email);
    }

    @GetMapping("/all-my-products/{email}")
    public List<Product> allMyProducts(@PathVariable String email) {
        return userService.allMyProducts(email);
    }

}
