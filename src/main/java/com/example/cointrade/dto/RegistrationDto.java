package com.example.cointrade.dto;

import com.example.cointrade.annotation.Unique;
import com.example.cointrade.repository.UserRepo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegistrationDto extends PasswordDto {



    @NotBlank
    @Pattern(regexp = "^[\\w.-]+@[\\w.-]+.\\w+$")
    @Unique(repositoryClass = UserRepo.class, methodName ="existsByEmail")
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
