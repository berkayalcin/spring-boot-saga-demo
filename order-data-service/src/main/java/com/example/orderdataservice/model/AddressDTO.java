package com.example.orderdataservice.model;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AddressDTO {
    private String city;
    private String town;
    private String fullAddress;
    private String zipCode;
}
