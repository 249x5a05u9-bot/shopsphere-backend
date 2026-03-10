package com.example.shopsphere.controller;


import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopsphere.dto.OrderAdminDTO;
import com.example.shopsphere.entity.Orders;
import com.example.shopsphere.entity.User;
import com.example.shopsphere.repository.OrderRepository;
import com.example.shopsphere.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @PostMapping
    public Orders place(@RequestBody Orders order) {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(email).orElseThrow();

        order.setUserId(user.getId());

        order.setStatus("PENDING");

        return orderRepository.save(order);
    }

    @GetMapping("/my")
    public List<OrderAdminDTO> myOrders() {

        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByEmail(email).orElseThrow();

        return orderRepository.findByUserId(user.getId());

    }

    @GetMapping("/admin")
    public List<OrderAdminDTO> adminOrders() {

        return orderRepository.findAllAdminOrders();

    }

    @PutMapping("/{id}/status")
    public Orders updateStatus(@PathVariable Long id, @RequestParam String status) {

        Orders order = orderRepository.findById(id).orElseThrow();

        order.setStatus(status);

        return orderRepository.save(order);

    }
}
