package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User editUser(User user);
    List<User> allUsers();
    ModelAndView allUsersOnPage(HttpServletRequest request, Model model);
    User vewUserByEmail(String userEmail);
    List<Product> myProducts(Long id);
    List<Product> myProductsList(String email);
    ModelAndView myProductsListOnPage(HttpServletRequest request, Model model);
}
