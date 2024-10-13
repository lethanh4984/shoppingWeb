package com.example.demo.service.UserProduct;

import com.example.demo.model.UserProduct;
import com.example.demo.repository.IUserProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProductService implements IUserProductService{

    @Autowired
    private IUserProductRepo iUserProductRepo;

    @Override
    public void save(UserProduct userProduct) {
        iUserProductRepo.save(userProduct);
    }

    @Override
    public Page<UserProduct> findAll(Pageable pageable,int userId) {
        return iUserProductRepo.findAll(pageable);
    }



    @Override
    public List<UserProduct> findAllProductByUserId(int userId) {
        List<UserProduct> userProductsFindByUserId= new ArrayList<>();
        for(UserProduct userProduct:iUserProductRepo.findAll()){
            if(userProduct.getUser().getId()==userId){
                userProductsFindByUserId.add(userProduct);
            }
        }
        return userProductsFindByUserId;
    }

    @Override
    public void deleteById(int userProductId) {
        iUserProductRepo.deleteById(userProductId);
    }
}
