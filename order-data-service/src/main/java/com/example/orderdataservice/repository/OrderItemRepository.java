package com.example.orderdataservice.repository;

import com.example.orderdataservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    List<OrderItem> findByOrderId(final String id);

    void deleteAllByOrderId(final String id);
}
