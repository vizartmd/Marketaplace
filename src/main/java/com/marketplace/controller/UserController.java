package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.model.dto.ProductForLikeUnlike;
import com.marketplace.model.dto.ProductIdToUserId;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import com.marketplace.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @PostMapping("/addUser")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/like-product/{productId}")
    public ModelAndView likeProductByUserId(@PathVariable String productId, HttpServletRequest request) {
        System.out.println("Liked productIdToUserId = " + productId);
        Product product = productService.vewProductById(Long.parseLong(productId));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        List myProducts = userService.myProducts(user.getId());
        List usersWhoLiked = product.getUsersWhoLiked();
        List usersWhoDisliked = product.getUsersWhoDisliked();
        if (myProducts.contains(product) || usersWhoDisliked.contains(user)) return new ModelAndView("redirect:/products");
        if (usersWhoLiked == null)  {
            usersWhoLiked = (List) new HashSet<User>();
            usersWhoLiked.add(user);
            System.out.println("User = " +  user.toString());
            productService.editProduct(product);
            return new ModelAndView("redirect:/products");
        }
        if (usersWhoLiked.contains(user)) return new ModelAndView("redirect:/products");
        usersWhoLiked.add(user);
        product.setLiked(true);
        product.setCheckedLike(true);
        System.out.println("product.isLiked() = " +  product.isLiked());
        System.out.println("product.isCheckedLike() = " +  product.isCheckedLike());
        productService.editProduct(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/unlike-product/{id}")
    public ModelAndView UnLikeProductByUserId(@PathVariable String id, HttpServletRequest request) {
        System.out.println("Id for unlike = " + id);
        Product product = productService.vewProductById(Long.parseLong(id));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        List myProducts = userService.myProducts(user.getId());
        List usersWhoLiked = product.getUsersWhoLiked();
        List usersWhoDisliked = product.getUsersWhoDisliked();
        if (myProducts.contains(product) || usersWhoLiked.contains(user)) return new ModelAndView("redirect:/products");
        if (usersWhoDisliked == null)  {
            usersWhoDisliked = (List) new HashSet<User>();
            product.setLiked(false);
            product.setCheckedLike(true);
            System.out.println("product.isLiked() = " +  product.isLiked());
            System.out.println("product.isCheckedLike() = " +  product.isCheckedLike());
            usersWhoDisliked.add(user);
            System.out.println("product.isLiked() = " +  product.isLiked());
            System.out.println("product.isCheckedLike() = " +  product.isCheckedLike());
            System.out.println("product.getUsersWhoLiked().contains(user) = " +  product.getUsersWhoLiked().contains(user));
            productService.editProduct(product);
            return new ModelAndView("redirect:/products");
        }
        if (usersWhoDisliked.contains(user)) return new ModelAndView("redirect:/products");
        product.setLiked(false);
        product.setCheckedLike(true);
        usersWhoDisliked.add(user);
        System.out.println("product.isLiked() = " +  product.isLiked());
        System.out.println("product.isCheckedLike() = " +  product.isCheckedLike());
        System.out.println("product.getUsersWhoLiked().contains(user) = " +  product.getUsersWhoLiked().contains(user));
        System.out.println("Product = " +  product.toString());
        productService.editProduct(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/reset-likes/{id}")
    public ModelAndView resetLikes(@PathVariable String id, HttpServletRequest request) {
        System.out.println("Unliked productIdToUserId = " + id);
        Product product = productService.vewProductById(Long.parseLong(id));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        product.setLiked(false);
        product.setCheckedLike(false);
        List usersWhoLiked = product.getUsersWhoLiked();
        List unLikedProducts = product.getUsersWhoDisliked();
        if (usersWhoLiked.contains(user)) {
            usersWhoLiked.clear();
        }
        if (unLikedProducts.contains(user)) {
            unLikedProducts.clear();
        }
        System.out.println("Product = " +  product.toString());
        productService.editProduct(product);
        return new ModelAndView("redirect:/products");
    }

}
