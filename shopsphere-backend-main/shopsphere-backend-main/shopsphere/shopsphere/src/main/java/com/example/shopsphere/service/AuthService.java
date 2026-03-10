package com.example.shopsphere.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopsphere.dto.AuthResponse;
import com.example.shopsphere.entity.User;
import com.example.shopsphere.repository.UserRepository;
import com.example.shopsphere.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public void signup(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
    }

    public AuthResponse login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(email);

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setName(user.getName());
        response.setRole(user.getRole());

        return response;
    }
}
