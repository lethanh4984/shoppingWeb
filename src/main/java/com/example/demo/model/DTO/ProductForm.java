package com.example.demo.model.DTO;

import com.example.demo.model.ProductTypes;
import com.example.demo.model.Reviews;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductForm {

    private int id;
    private int productTypeId;
    private String color;
    private String size;
    private double price;
    private double discount;
    private List<Reviews> reviews;
    private String image;
    private String description;
}
