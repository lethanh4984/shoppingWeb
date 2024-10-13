package com.example.demo.controller.customer;

import com.example.demo.model.DTO.ReviewForm;
import com.example.demo.model.DTO.UserProductForm;
import com.example.demo.model.Products;
import com.example.demo.model.Reviews;
import com.example.demo.model.User;
import com.example.demo.model.UserProduct;
import com.example.demo.service.Products.ProductService;
import com.example.demo.service.Review.ReviewService;
import com.example.demo.service.UserProduct.UserProductService;
import com.example.demo.service.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserProductService userProductService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{userId}")
    public List<UserProduct> showListBought(@PageableDefault(value = 5)Pageable pageable,
                                            @PathVariable("userId")int userId){

        User user= userService.findById(userId).get();
        List<UserProduct> userProductList  = userProductService.findAllProductByUserId(userId);
        double total = 0;
        for(UserProduct userProductOfUser:userProductList){
            total += userProductOfUser.getMoney();
        }
        user.setTotalMoney(total);
        //tong so tien user da mua
        return userProductService.findAllProductByUserId(userId);
    }

    @PostMapping("/buyProducts")
    public String buyProducts(@RequestBody UserProductForm userProductForm){

            UserProduct userProduct = new UserProduct();
        User user =  userService.findById(userProductForm.getUserId()).get();
        Products product = productService.findById(userProductForm.getProductId()).get();

        userProduct.setUser(user);
        userProduct.setNote(userProductForm.getNote());
        userProduct.setSize(product.getSize());
        userProduct.setMoney(product.getPrice());
        userProduct.setCreatedAt(userProductForm.getCreatedAt());
        userProduct.setProductId(userProductForm.getProductId());



        userProductService.save(userProduct);


        return "buy product" ;
    }

    @DeleteMapping("/denyProduct/{userProductId}")
    public void denyProduct(@PathVariable int userProductId){
        userProductService.deleteById(userProductId);
    }

    @GetMapping("/showListProduct")
    public Page<Products> products(@PageableDefault(value = 10)Pageable pageable){
        return productService.findAll(pageable);
    }

    @GetMapping("/showProductInfo/{productId}")
    public Products productInfo(@PathVariable int productId){

       Products product= productService.findById(productId).get();

       return product;
    }

    @PostMapping("/reviews")
    public void writeReview(@RequestBody ReviewForm reviewForm){
        Products product = productService.findById(reviewForm.getProductId()).get();

        User user = userService.findById(reviewForm.getUserId()).get();

        Reviews reviews = new Reviews();
        reviews.setUsers(user);
        reviews.setReview(reviewForm.getReview());
        reviews.setRate(reviewForm.getRate());
        reviews.setProductId(reviewForm.getProductId());

        product.setReviews(reviewService.findReviewByProductId(reviewForm.getProductId()));
        productService.save(product);
        reviewService.save(reviews);
    }

    @DeleteMapping("/deleteReview/{reviewId}")
    public void deleteReview(@PathVariable int reviewId){
        reviewService.delete(reviewId);

        Reviews review = reviewService.findById(reviewId);
        Products product = productService.findById(review.getProductId()).get();
        product.getReviews().removeIf(reviews -> reviews.getUsers().getId() == review.getUsers().getId());
        productService.save(product);

    }
}
