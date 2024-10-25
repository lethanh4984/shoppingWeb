package com.example.demo.controller.admin;

import com.example.demo.model.DTO.UserForm;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.productType.IProductTypeService;
import com.example.demo.service.products.IProductService;
import com.example.demo.service.role.IRoleService;
import com.example.demo.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/admin")
public class AccountController {
    @Autowired
    private IProductTypeService iProductTypeService;

    @Autowired
    private IProductService iProductService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @GetMapping("/accounts")
    public Page<User> homeAd(@PageableDefault(value = 10) Pageable pageable){
       return userService.findAll(pageable);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/addAccount")
    public User addAccount(@RequestBody UserForm userDTO){

        Role role = roleService.findById(userDTO.getRoleId()).get();
        User user = new User();
        user.setId(userDTO.getId());
        user.setAddress(userDTO.getAddress());
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        String password = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(password);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(Collections.singleton(role));
        user.setEmail(userDTO.getEmail());

        userService.save(user);

        return user;
    }

    @PutMapping("/updateAccount")
    public void updateAccount(@RequestBody UserForm userDTO){

        User user = userService.findById(userDTO.getId()).get();
        user.setAddress(userDTO.getAddress());
        user.setUserName(userDTO.getUserName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        userService.save(user);

    }

    @DeleteMapping("/deleteAccount/{id}")
    public String delete(@PathVariable int id){
        userService.delete(id);

        return "delete user with userId "+id;
    }


}
