package com.example.demo.repository;

import com.example.demo.model.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductTypeRepo extends JpaRepository<ProductTypes,Integer> {
}
