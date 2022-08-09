package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.repository.ProductRepository;
import com.marketplace.repository.UserRepository;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        List<User> listUsers = (List<User>) userService.allUsers();
        for (User u : listUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                return "signup_form";
            }
        }
        if (!listUsers.contains(user)) {
            userService.createUser(user);
            return "register_success";
        }
        return "signup_form";
    }

    @GetMapping("/users")
    public String listUsers(HttpServletRequest request, Model model) {
        List<User> listUsers = (List<User>) userService.allUsers();
        model.addAttribute("listUsers", listUsers);
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        return "users";
    }

    @GetMapping("/products")
    public String listProducts(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        List<Product> listProducts = productService.allProducts();
        model.addAttribute("user", user);
        model.addAttribute("listProducts", listProducts);
        List<Product> listMyProducts = userService.myProductsList(principal.getName());
        model.addAttribute("listMyProducts", listMyProducts);
        return "products";
    }

    @GetMapping("/myproducts")
    public String myProducts(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        List<Product> listMyProducts = userService.myProductsList(principal.getName());
        model.addAttribute("listMyProducts", listMyProducts);
        return "myproducts";
    }

    @RequestMapping(value = "/add-product-to-user/{productId}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView addProductToUser (@PathVariable String productId, HttpServletRequest request) {
        System.out.println("productIdToUserId = " + productId);
        Product product = productService.vewProductById(Long.parseLong(productId));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        if (!product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().add(product);
            userService.editUser(user);
        }
        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value = "/remove-from-my-products/{productId}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProductFromUser (@PathVariable String productId, HttpServletRequest request) {
        System.out.println("productIdToUserId = " + productId);
        Product product = productService.vewProductById(Long.parseLong(productId));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        if (user.getProductList().contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().remove(product);
            userService.editUser(user);
        }
        return new ModelAndView("redirect:/products");
    }

    @RequestMapping(value = "/delete-product/{id}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProduct (@PathVariable String id, HttpServletRequest request) {
        System.out.println("productIdToUserId = " + id);
        Product product = productService.vewProductById(Long.parseLong(id));
        Principal principal = request.getUserPrincipal();
        System.out.println("principal.getName() = " + principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        List<Product> userProducts = user.getProductList();
        List<Product> allProducts = user.getProductList();
        if (!userProducts.contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            product.setCheckedLike(false);
            productService.deleteProduct(product);
            user.getProductList().remove(product);
            System.out.println("Product with id " + id + " was removed!");
            System.out.println(allProducts.toString());
            System.out.println(allProducts.size());
        } else {
            System.out.println("Some users contains this product. Remove it from user and after you'll can delete this product.");
        }
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/addProduct")
    public ModelAndView createProduct (HttpServletRequest request,  Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        return new ModelAndView("create_product");
    }

    @PostMapping("/insertProduct/{product}")
    public ModelAndView insertProduct (@PathVariable Product product, HttpServletRequest request,  Model model) {
        Principal principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        productService.createProduct(product);
        return new ModelAndView("redirect:/products");
    }

    @PostMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("signup_form");
    }
}
