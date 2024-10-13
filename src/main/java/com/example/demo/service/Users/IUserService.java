package com.example.demo.service.Users;

import com.example.demo.model.DTO.ROLENAME;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService {

    Page<User> findAll(ROLENAME roleName, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(int userId);

    void save(User user);

    void delete(int userId);

}
