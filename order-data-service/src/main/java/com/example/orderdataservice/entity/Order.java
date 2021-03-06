package com.example.orderdataservice.entity;

import com.example.orderdataservice.model.AddressDTO;
import com.example.orderdataservice.model.UserDTO;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @Embedded
    private AddressDTO address;

    @Embedded
    private UserDTO buyer;
}
