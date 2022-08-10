package com.marketplace.service;

import com.marketplace.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Product editProduct(Product product);
    void deleteProduct(Product product);
    Product vewProductById(Long id);
    List<Product> allProducts();
    List<Product> myProducts(String email);
    List<Product> likeProducts(Long userId);
    List<Product> unLikeProducts(Long userId);
    Page<Product> findPage(int pageNumber);

}
