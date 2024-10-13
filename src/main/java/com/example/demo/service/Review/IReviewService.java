package com.example.demo.service.Review;

import com.example.demo.model.Reviews;

import java.util.List;

public interface IReviewService {
    List<Reviews> findByProductId(int productId);
}
