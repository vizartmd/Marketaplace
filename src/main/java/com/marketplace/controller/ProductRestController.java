package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.service.ProductService;
import org.assertj.core.util.Preconditions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/view-product-by-id/{id}")
    public Product viewProductById(@PathVariable Long id) {
        return productService.vewProductById(id);
    }

    @PostMapping("/edit-product")
    public Product editProduct(@RequestBody Product product) {
        return productService.editProduct(product);
    }

    @GetMapping("/delete-product/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/all-products")
    public List<Product> allProducts() {
        return productService.allProducts();
    }

    @GetMapping("/all-my-liked-products/{email}")
    public List<Product> allMyLikedProducts(@PathVariable String email) {
        return productService.allMyLikedProducts(email);
    }

    @GetMapping("/all-my-disliked-products/{email}")
    public List<Product> allMyDislikedProducts(@PathVariable String email) {
        return productService.allMyDislikedProducts(email);
    }

    @GetMapping("/like-products/{id}/email/{email}")
    Product likeProducts(@PathVariable Long id, @PathVariable String email) {
        return productService.likeProducts(id, email);
    };

    @GetMapping("/dislike-products/{id}/email/{email}")
    Product unLikeProducts(@PathVariable Long id, @PathVariable String email) {
        return productService.unLikeProducts(id, email);
    };

    @GetMapping("/reset-likes-of-product/{id}/email/{email}")
    Product resetLikes(@PathVariable Long id, @PathVariable String email) {
        return productService.resetLikes(id, email);
    };

}
