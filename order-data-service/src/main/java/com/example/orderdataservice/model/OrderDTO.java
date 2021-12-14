package com.example.orderdataservice.model;

import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private AddressDTO address;
    private UserDTO buyer;
    private Set<OrderItemDTO> orderItems;
}
