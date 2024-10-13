package com.example.demo.model.DTO;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserLogin implements UserDetails {

    @Serial
    private static final long serialVersionUID =1L;
    private int id;
    @Getter
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> role;

    public UserLogin(int id, String userName, String password, Collection<? extends GrantedAuthority> role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public static UserLogin build(User appUser){
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role appRole : appUser.getRole()) {
            authorities.add(new SimpleGrantedAuthority(appRole.getRoleName()));
        }
        System.out.println("dang build user principle. Chay tiep buoc nao");
        return new UserLogin(appUser.getId(), appUser.getUserName(), appUser.getPassword(), authorities);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
