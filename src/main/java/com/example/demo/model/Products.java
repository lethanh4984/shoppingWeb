package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "price_discount")
    private double priceDiscount;

    @Column(name = "size")
    private String size;

    @Column(name = "img")
    private String image;

    @Column(name = "status")
    private int status;

    @OneToMany
    @JoinColumn(name = "product_type_id")
    private List<ProductTypes> productTypes;

    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Reviews> reviews;

    @Override
    public String toString() {
        return "Products{" +
                "color='" + color + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", priceDiscount=" + priceDiscount +
                ", size='" + size + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", productTypes=" + productTypes +
                ", reviews=" + reviews +
                '}';
    }
}
