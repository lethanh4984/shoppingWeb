package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "rate")
    private double rate;

    @Column(name = "products_id")
    private int productId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @Column(name = "review")
    private String review;
}