package com.marketplace.repository;

import com.marketplace.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    Set<Product> getUnlikedProductsByUserEmail(User user);
//    Set<Product> getLikedProductsByUserEmail(User user);
//    Set<Product> getProductsByUserEmail(User user);
}
