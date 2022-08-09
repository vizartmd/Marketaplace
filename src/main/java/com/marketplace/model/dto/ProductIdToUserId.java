package com.marketplace.model.dto;

public class ProductIdToUserId {
    private Long userId;
    private Long productId;

    public ProductIdToUserId() {
    }

    public ProductIdToUserId(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ProductIdToUserId{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
