package com.example.shopsphere.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderAdminDTO {

    private Long orderId;
    private String userName;
    private String productName;
    private double price;
    private String status;

}
