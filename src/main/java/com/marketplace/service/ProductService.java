package com.marketplace.service;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product editProduct(Product product);
    void deleteProduct(Long id);
    Product vewProductById(Long id);
    List<Product> allProducts();
    Product likeProducts(Long id, String email);
    Product unLikeProducts(Long id, String email);
    Product resetLikes(Long id, String email);
    Page<Product> findPage(int currentPage);
    List<Product> allMyLikedProducts(String email);
    List<Product> allMyDislikedProducts(String email);
}
