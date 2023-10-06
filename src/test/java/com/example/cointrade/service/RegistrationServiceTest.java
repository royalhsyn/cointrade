package com.example.cointrade.service;

import com.example.cointrade.dto.RegistrationDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    private RegistrationService service;

    @Test
    void registerAndExceptOk() {
//        RegistrationDto dto=new RegistrationDto();
//        dto.setUsername("u1");
//        dto.setPassword("p1");
//        dto.setConfirmPassword("p1");
//        service.register(dto);
    }
    @Test
    void registerAndException() {
//        RegistrationDto dto=new RegistrationDto();
//        dto.setPassword("p1");
//        dto.setConfirmPassword("p1");
//        service.register(dto);
    }

    @Test
    void confirm() {
    }

    @Test
    void activateUser() {
    }

    @Test
    void sendMail() {
    }

    @Test
    void resend() {
    }
}