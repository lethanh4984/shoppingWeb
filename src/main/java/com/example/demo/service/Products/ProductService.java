package com.example.demo.service.Products;

import com.example.demo.model.Products;
import com.example.demo.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepo productRepo;


    @Override
    public Page<Products> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Optional<Products> findById(int productId) {
        return productRepo.findById(productId);
    }

    @Override
    public void save(Products product) {
        productRepo.save(product);
    }

    @Override
    public void deleteById(int productId) {
        productRepo.deleteById(productId);
    }
}
