package com.example.demo.service.Role;

import com.example.demo.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRoleService  {

    Page<Role> findAll(Pageable pageable);

    Optional<Role> findById(int roleId);

    void save(Role role);

    void delete(Role role);

}
