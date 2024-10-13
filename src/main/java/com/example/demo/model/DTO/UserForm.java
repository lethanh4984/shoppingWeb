package com.example.demo.model.DTO;

import lombok.Data;

@Data
public class UserForm {

    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int phoneNumber;
    private String address;
    private String email;
    private int roleId;

    @Override
    public String toString() {
        return "UserForm{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
