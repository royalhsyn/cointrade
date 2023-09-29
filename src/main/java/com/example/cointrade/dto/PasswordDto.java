package com.example.cointrade.dto;

import com.example.cointrade.annotation.MatchPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MatchPassword
public class PasswordDto {

    @NotBlank()
    private String password;

    @NotBlank()
    private String confirmPassword;
}
