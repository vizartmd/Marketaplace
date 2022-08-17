package com.marketplace.controller;

import com.marketplace.model.Product;
import com.marketplace.model.User;
import com.marketplace.service.ProductService;
import com.marketplace.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductViewController {

    private final UserService userService;
    private final ProductService productService;
    private Product product;
    private Principal principal;
    private User user;

    public ProductViewController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/add_product")
    public ModelAndView createProductView (HttpServletRequest request,  Model model) {
        principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        return new ModelAndView("create_product");
    }

    @PostMapping("/add_product")
    public ModelAndView insertProductView (Product product, HttpServletRequest request, Model model) {
        principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        List<Product> products = productService.allProducts();
        if (!products.contains(product)) {
            productService.createProduct(product);
            return new ModelAndView("redirect:/products");
        }
        return new ModelAndView("create_product");
    }

    @GetMapping("/view-product/{id}")
    public ModelAndView viewProductByIdView (@PathVariable String id, HttpServletRequest request,  Model model) {
        principal = request.getUserPrincipal();
        product = productService.vewProductById(Long.parseLong(id));
        model.addAttribute("email", principal.getName());
        model.addAttribute("product", product);
        return new ModelAndView("view_product");
    }

    @GetMapping("/edit-product/{id}")
    public ModelAndView editProductByIdView (@PathVariable String id, HttpServletRequest request,  Model model) {
        principal = request.getUserPrincipal();
        product = productService.vewProductById(Long.parseLong(id));
        model.addAttribute("email", principal.getName());
        model.addAttribute("product", product);
        return new ModelAndView("edit_product");
    }

    @PostMapping("/update_product")
    public ModelAndView updateProductView (Product product, HttpServletRequest request,  Model model) {
        principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        productService.editProduct(product);
        return new ModelAndView("redirect:/myproducts");
    }

    @GetMapping("/myproducts")
    public ModelAndView myProductsView(HttpServletRequest request, Model model) {
        principal = request.getUserPrincipal();
        model.addAttribute("email", principal.getName());
        List<Product> listMyProducts = userService.myProductsList(principal.getName());
        model.addAttribute("listMyProducts", listMyProducts);
        return new ModelAndView("myproducts");
    }

    @PostMapping("/like-product/{productId}/currentPage/{currentPage}")
    public ModelAndView likeProductByUserEmailView(@PathVariable String productId, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(productId));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        List myProducts = userService.myProducts(user.getId());
        List usersWhoLiked = product.getUsersWhoLiked();
        List usersWhoDisliked = product.getUsersWhoDisliked();
        if (myProducts.contains(product) || usersWhoDisliked.contains(user)) return new ModelAndView("redirect:/products/page/" + currentPage);
        if (usersWhoLiked == null)  {
            usersWhoLiked = (List) new HashSet<User>();
            usersWhoLiked.add(user);
            product.setLiked(true);
            product.setCheckedLike(true);
            productService.editProduct(product);
            return new ModelAndView("redirect:/products/page/" + currentPage);
        }
        if (usersWhoLiked.contains(user)) return new ModelAndView("redirect:/products/page/" + currentPage);
        usersWhoLiked.add(user);
        product.setLiked(true);
        product.setCheckedLike(true);
        productService.editProduct(product);
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @PostMapping("/unlike-product/{id}/currentPage/{currentPage}")
    public ModelAndView UnLikeProductByUserEmailView(@PathVariable String id, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(id));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        if (userService.myProducts(user.getId()).contains(product)
                || product.getUsersWhoLiked().contains(user)) return new ModelAndView("redirect:/products/page/" + currentPage);
        if (product.getUsersWhoDisliked() == null)  {
            product.setLiked(false);
            product.setCheckedLike(true);
            product.getUsersWhoDisliked().add(user);
            productService.editProduct(product);
            return new ModelAndView("redirect:/products/page/" + currentPage);
        }
        if (product.getUsersWhoDisliked().contains(user)) return new ModelAndView("redirect:/products/page/" + currentPage);
        product.setLiked(false);
        product.setCheckedLike(true);
        product.getUsersWhoDisliked().add(user);
        productService.editProduct(product);
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @PostMapping("/reset-likes/{id}/currentPage/{currentPage}")
    public ModelAndView resetLikesView(@PathVariable String id, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(id));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        product.setLiked(false);
        product.setCheckedLike(false);
        product.getUsersWhoLiked().remove(user);
        product.getUsersWhoDisliked().remove(user);
        productService.editProduct(product);
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @GetMapping("/products")
    public ModelAndView getAllPagesView(Model model, HttpServletRequest request){
        return getOnePageView(model, 1, request);
    }

    @GetMapping("/products/page/{currentPage}")
    public ModelAndView getOnePageView(Model model, @PathVariable("currentPage") int currentPage, HttpServletRequest request) {
        Page<Product> page = productService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> productsOnPage = page.getContent().stream().sorted().collect(Collectors.toList());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listProducts", productsOnPage);

        Principal principal = request.getUserPrincipal();
            model.addAttribute("email", principal.getName());
        User user = userService.vewUserByEmail(principal.getName());
        model.addAttribute("user", user);
        List<Product> listMyProducts = userService.myProductsList(principal.getName());
        model.addAttribute("listMyProducts", listMyProducts);
        return new ModelAndView("products");
    }

    @RequestMapping(value = "/add-product-to-user/{productId}/currentPage/{currentPage}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView addProductToUserView (@PathVariable String productId, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(productId));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        if (!product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().add(product);
            userService.editUser(user);
        }
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @RequestMapping(value = "/remove-from-my-products/{productId}/currentPage/{currentPage}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProductFromUserView (@PathVariable String productId, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(productId));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        if (user.getProductList().contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().remove(product);
            userService.editUser(user);
        }
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @RequestMapping(value = "/remove-from-my-products-list/{productId}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProductFromMyProductsListView (@PathVariable String productId, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(productId));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        if (user.getProductList().contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().remove(product);
            userService.editUser(user);
        }
        if (user.getProductList().isEmpty()) return new ModelAndView("redirect:/products");
        return new ModelAndView("redirect:/myproducts");
    }

    @RequestMapping(value = "/remove-from-my-products-list/{productId}/currentPage/{currentPage}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProductFromMyProductView (@PathVariable String productId, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(productId));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        if (user.getProductList().contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            user.getProductList().remove(product);
            userService.editUser(user);
        }
        if (user.getProductList().isEmpty()) return new ModelAndView("redirect:/products");
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

    @RequestMapping(value = "/delete-product/{id}/currentPage/{currentPage}", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView removeProductView (@PathVariable String id, @PathVariable String currentPage, HttpServletRequest request) {
        product = productService.vewProductById(Long.parseLong(id));
        principal = request.getUserPrincipal();
        user = userService.vewUserByEmail(principal.getName());
        List<Product> userProducts = user.getProductList();
        if (!userProducts.contains(product) && !product.getUsersWhoLiked().contains(user) && !product.getUsersWhoDisliked().contains(user)) {
            product.setCheckedLike(false);
            productService.deleteProduct(Long.parseLong(id));
            userProducts.remove(product);
        } else {
            return new ModelAndView("redirect:/products/page/" + currentPage);
        }
        return new ModelAndView("redirect:/products/page/" + currentPage);
    }

}
