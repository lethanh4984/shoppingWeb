package com.example.demo.service.Review;

import com.example.demo.model.Reviews;
import com.example.demo.repository.IReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements IReviewService{

    @Autowired
    private IReviewRepo reviewRepo;

    @Override
    public List<Reviews> findReviewByProductId(int productId) {
        List<Reviews> reviewsOfProduct = new ArrayList<>();
        for(Reviews reviews: reviewRepo.findAll()){
            if(reviews.getProductId()==productId){
                reviewsOfProduct.add(reviews);
            }
        }

        return reviewsOfProduct;
    }

    @Override
    public void save(Reviews review) {
        reviewRepo.save(review);
    }

    @Override
    public void delete(int reviewId) {
        reviewRepo.deleteById(reviewId);
    }

    @Override
    public Reviews findById(int reviewId) {
        return reviewRepo.findById(reviewId).get();
    }
}
