package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.model.dto.ProductDto;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

}
