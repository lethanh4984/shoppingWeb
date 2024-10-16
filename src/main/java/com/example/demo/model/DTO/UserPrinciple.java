package com.example.demo.model.DTO;


import com.example.demo.model.Role;
import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserPrinciple implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String userName;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    //    chuyen tu user trong model -> User co kha nang phan quyen UserPrinciple
    public static UserPrinciple build(User user) {
//        quyen de xac thuc -> GrantedAuthority
        List<GrantedAuthority> author = new ArrayList<>();
        for (Role role : user.getRole()) {
            author.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new UserPrinciple(user.getUserName(), user.getPassword(), author);
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
