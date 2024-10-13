package com.example.demo.service.UserProduct;

import com.example.demo.model.UserProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserProductService {
    void save(UserProduct userProduct);

    Page<UserProduct> findAll(Pageable pageable,int userId);

    List<UserProduct> findAllProductByUserId(int userId);

    void deleteById(int userProductId);
}
