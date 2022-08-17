package com.marketplace.service.impl;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product editProduct(Product product) {
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product vewProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public Page<Product> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> allMyLikedProducts(String email) {
        User user = userRepository.findByEmail(email);
        List<Product> products = productRepository.findAll();
        products.removeIf(p -> !p.getUsersWhoLiked().contains(user));
        return products;
    }

    @Override
    public List<Product> allMyDislikedProducts(String email) {
        User user = userRepository.findByEmail(email);
        List<Product> products = productRepository.findAll();
        products.removeIf(p -> !p.getUsersWhoDisliked().contains(user));
        return products;
    }

    @Override
    public Product likeProducts(Long id, String email) {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(id).get();
        if (user.getProductList().contains(productRepository.getById(id)) || product.isCheckedLike()) {
            return null;
        }
        product.getUsersWhoLiked().add(user);
        product.setLiked(true);
        product.setCheckedLike(true);
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product unLikeProducts(Long id, String email) {
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(id).get();
        if (user.getProductList().contains(productRepository.getById(id)) || product.isCheckedLike()) {
            return null;
        }
        product.getUsersWhoDisliked().add(user);
        product.setLiked(false);
        product.setCheckedLike(true);
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product resetLikes(Long id, String email) {
        Product product = productRepository.findById(id).get();
        User user = userRepository.findByEmail(email);
        product.getUsersWhoLiked().remove(user);
        product.getUsersWhoDisliked().remove(user);
        product.setLiked(false);
        product.setCheckedLike(false);
        productRepository.saveAndFlush(product);
        return product;
    }

}
