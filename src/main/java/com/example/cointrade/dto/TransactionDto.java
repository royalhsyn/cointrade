package com.example.cointrade.dto;

public record TransactionDto(
        Long id,
        Double quantity,
        Double totalPrice,
        Long userId,
        Long coinId

) {
}
