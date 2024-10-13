package com.example.demo.model.DTO;

import lombok.Data;

@Data
public class ReviewForm {

    private int userId;
    private int productId;
    private String review;
    private int rate;
}
