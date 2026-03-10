package com.example.shopsphere.dto;


import lombok.Data;

@Data
public class AuthResponse {

    private String token;

    private String name;

    private String role;

    public static class OrderAdminDTO {
    }
}
