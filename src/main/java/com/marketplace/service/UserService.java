package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);
    User editUser(User user);
    User vewUserById(Long id);
    Iterable<User> allUsers();
    void deleteUser(User user);
    void deleteUserById(Long id);
    User vewUserByEmail(String userEmail);
    List<Product> myProducts(Long id);
    List<Product> myProductsList(String email);
}
