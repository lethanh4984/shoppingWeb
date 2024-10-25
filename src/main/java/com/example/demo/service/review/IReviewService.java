package com.example.demo.service.review;

import com.example.demo.model.Reviews;

import java.util.List;

public interface IReviewService {

    List<Reviews> findReviewByProductId(int productId);

    void save(Reviews review);

    void delete(int reviewId);

    Reviews findById(int reviewId);
}
