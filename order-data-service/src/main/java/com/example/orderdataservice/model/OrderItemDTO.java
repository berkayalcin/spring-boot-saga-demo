package com.example.orderdataservice.model;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private String id;
    private String orderId;
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
}
