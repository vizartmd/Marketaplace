package com.marketplace.service.impl;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User vewUserByEmail(String userEmail) {
        return userRepository.findAll().stream().filter(u -> u.getEmail().equals(userEmail)).findFirst().orElse(null);
    }

    @Override
    public List<Product> myProducts(Long userId) {
        return userRepository.findById(userId).get().getProductList();
    }

    @Override
    public List<Product> myProductsList(String email) {
        User user = userRepository.findByEmail(email);
        return user.getProductList();
    }

    @Override
    public ModelAndView myProductsListOnPage(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        User user = userRepository.findByEmail(principal.getName());
        List<Product> listMyProducts = userRepository.findByEmail(user.getEmail()).getProductList();
        model.addAttribute("listMyProducts", listMyProducts);
        return new ModelAndView("redirect:/myproducts");
    }

    @Override
    public ModelAndView allUsersOnPage(HttpServletRequest request, Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        return new ModelAndView("all-users");
    }
}
