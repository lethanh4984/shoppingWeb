package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user_product")
public class UserProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "money",nullable = false)
    private double money=0.0;

    @Column(name = "size")
    private String size;

    @Column(name = "add_time")
    private LocalDateTime createdAt;

    @Column(name = "note")
    private String note;

}
