package com.marketplace.service;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.UserRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product vewProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> myProducts(String email) {
        User user = userRepository.findByEmail(email);
        return user.getProductList();
    }

    @Override
    public List<Product> likeProducts(Long userId) {
        return null;
    }

    @Override
    public List<Product> unLikeProducts(Long userId) {
        return null;
    }

}
