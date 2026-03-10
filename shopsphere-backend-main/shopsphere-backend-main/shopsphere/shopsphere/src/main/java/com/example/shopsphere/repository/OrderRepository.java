package com.example.shopsphere.repository;

import java.util.List;

import com.example.shopsphere.dto.AuthResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shopsphere.dto.OrderAdminDTO;
import com.example.shopsphere.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("""
			SELECT new com.shopsphere.dto.OrderAdminDTO(
			o.id,
			u.name,
			p.name,
			p.price,
			o.status
			)
			FROM Orders o
			LEFT JOIN User u ON u.id = o.userId
			LEFT JOIN Product p ON p.id = o.productId
			""")
    List<OrderAdminDTO> findAllAdminOrders();

    @Query("""
			SELECT new com.shopsphere.dto.OrderAdminDTO(
			o.id,
			u.name,
			p.name,
			p.price,
			o.status
			)
			FROM Orders o
			LEFT JOIN User u ON u.id = o.userId
			LEFT JOIN Product p ON p.id = o.productId
			WHERE o.userId = ?1
			""")
    List<OrderAdminDTO> findByUserId(Long id);
}