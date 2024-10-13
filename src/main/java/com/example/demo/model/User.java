package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private  String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private int phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private int status;

    @Column(name = "money")
    private double totalMoney=0.0;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role;


    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", totalMoney=" + totalMoney +
                ", role=" + role +
                '}';
    }
}
