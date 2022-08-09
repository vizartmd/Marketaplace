package com.marketplace.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ProductDto {

    private String title;
    private String description;
    private double price;
    private Long userId;

    public ProductDto() {
    }

    public ProductDto(String title, String description, double price, Long userId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.userId = userId;
    }

    public ProductDto(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
