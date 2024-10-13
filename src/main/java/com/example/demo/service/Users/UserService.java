package com.example.demo.service.Users;

import com.example.demo.model.DTO.ROLENAME;
import com.example.demo.model.DTO.UserLogin;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepo userRepo;

    @Override
    public Page<User> findAll(ROLENAME roleName, Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepo.findAll(pageable);
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepo.findById(userId);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void delete(int userId) {

        userRepo.deleteById(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepo.findByUserName(username);

        if(appUser ==null){
            throw new UsernameNotFoundException("user cant find");
        }
        System.out.println("dang build user detail");
        return UserLogin.build(appUser);

    }
}
