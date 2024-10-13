package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Products,Integer> {
}
