package com.example.cointrade.dto;

import java.util.Date;

public record UserDto(
        String firstName,
        String lastName,
        Date birthdate,
        String email,
        Double cash,
        Long walletId
) {
}
