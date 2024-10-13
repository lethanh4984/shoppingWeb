package com.example.demo.service.Review;

import com.example.demo.model.Reviews;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService{
    @Override
    public List<Reviews> findByProductId(int productId) {
        return List.of();
    }
}
