package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product_types")
public class ProductTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @Column(name = "name")
    private String name;

   @Column(name = "note")
    private String note;

}