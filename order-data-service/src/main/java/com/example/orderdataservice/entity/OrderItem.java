package com.example.orderdataservice.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String orderId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
}