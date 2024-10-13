package com.example.demo.model.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserProductForm {

    private int userId;
    private int productId;
    private LocalDateTime createdAt;
    private String note;

    @Override
    public String toString() {
        return "UserProductForm{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", note='" + note + '\'' +
                '}';
    }
}
