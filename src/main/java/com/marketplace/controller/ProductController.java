package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.model.dto.ProductDto;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

}
