package com.example.cointrade.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationResponseDto {

    private String authToken;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
