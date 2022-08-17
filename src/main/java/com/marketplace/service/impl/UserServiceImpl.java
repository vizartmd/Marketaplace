package com.marketplace.service.impl;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.ProductRepository;
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

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        userRepository.saveAndFlush(user);
        return user;
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
    public List<Product> addToMyProducts(Long id, String email) {
        Product product = productRepository.findById(id).get();
        User user = userRepository.findByEmail(email);
        user.getProductList().add(product);
        userRepository.saveAndFlush(user);
        return user.getProductList();
    }

    @Override
    public List<Product> removeFromMyProducts(Long id, String email) {
        Product product = productRepository.findById(id).get();
        User user = userRepository.findByEmail(email);
        user.getProductList().remove(product);
        userRepository.saveAndFlush(user);
        return user.getProductList();
    }

    @Override
    public List<Product> allMyProducts(String email) {
        User user = userRepository.findByEmail(email);
        return user.getProductList();
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
