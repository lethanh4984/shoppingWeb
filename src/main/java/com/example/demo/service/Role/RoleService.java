package com.example.demo.service.Role;

import com.example.demo.model.Role;
import com.example.demo.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRepo.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(int roleId) {
        return roleRepo.findById(roleId);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void delete(Role role) {

    }
}
