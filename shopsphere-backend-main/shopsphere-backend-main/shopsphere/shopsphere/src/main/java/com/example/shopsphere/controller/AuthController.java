package com.example.shopsphere.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopsphere.dto.AuthResponse;
import com.example.shopsphere.dto.LoginRequest;
import com.example.shopsphere.entity.User;
import com.example.shopsphere.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {

        authService.signup(user);

        return "User Registered";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        return authService.login(request.getEmail(), request.getPassword());

    }
}