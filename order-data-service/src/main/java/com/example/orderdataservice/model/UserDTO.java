package com.example.orderdataservice.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String identityNumber;
}
