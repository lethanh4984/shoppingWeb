package com.example.demo.service.ProductType;

import com.example.demo.model.ProductTypes;
import com.example.demo.repository.IProductTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductTypeService implements IProductTypeService {

    @Autowired
    private IProductTypeRepo productTypeRepo;

    @Override
    public Page<ProductTypes> findAll(Pageable pageable) {
        return productTypeRepo.findAll(pageable);
    }

    @Override
    public Optional<ProductTypes> findById(int productTypeId) {
        return productTypeRepo.findById(productTypeId);
    }

    @Override
    public void save(ProductTypes productType) {
        productTypeRepo.save(productType);
    }

    @Override
    public void delete(int productTypeId) {
        productTypeRepo.deleteById(productTypeId);
    }
}
