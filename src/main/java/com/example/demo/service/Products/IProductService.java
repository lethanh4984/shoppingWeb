package com.example.demo.service.Products;

import com.example.demo.model.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IProductService {
    Page<Products> findAll(Pageable pageable);

    Optional<Products> findById(int productId);

    void save(Products product) ;

    void deleteById(int productId);
}
