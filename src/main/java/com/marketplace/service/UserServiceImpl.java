package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public User vewUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUser(User user) {
    }

    @Override
    public void deleteUserById(Long id) {

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
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
