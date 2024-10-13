package com.example.demo.service.Users;

import com.example.demo.model.DTO.ROLENAME;
//import com.example.demo.model.DTO.UserLogin;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

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
        // Retrieve the user from the database
        User user = userRepo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Create UserDetails object with the user's authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }
}
