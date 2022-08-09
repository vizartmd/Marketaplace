package com.marketplace.model.dto;

public class ProductForLikeUnlike {
    private String userEmail;
    private Long productId;

    public ProductForLikeUnlike(String userEmail, Long productId) {
        this.userEmail = userEmail;
        this.productId = productId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ProductForLikeUnlike{" +
                "userEmail='" + userEmail + '\'' +
                ", productId=" + productId +
                '}';
    }
}
