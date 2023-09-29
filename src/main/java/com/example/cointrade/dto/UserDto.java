package com.example.cointrade.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private Date birthdate;

    private String email;

    private Double cash;

    private List<Long> walletId;

    private List<Long> transactionId;
}
