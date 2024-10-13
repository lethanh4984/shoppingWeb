package com.example.demo.service.ProductType;

import com.example.demo.model.ProductTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductTypeService {

    Page<ProductTypes> findAll(Pageable pageable);

    Optional<ProductTypes>findById(int productTypeId);

    void save(ProductTypes product);

    void delete(int productTypeId);
}
