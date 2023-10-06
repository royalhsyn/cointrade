package com.example.cointrade.controller;

import com.example.cointrade.dto.UserDto;
import com.example.cointrade.entity.User;
import com.example.cointrade.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/v1/api/user")
public class UserController {

    private final UserService service;



    @GetMapping
//    @PreAuthorize("hasAuthority('READ_USER')")
    public List<UserDto> list(@AuthenticationPrincipal String username,
                              @Spec(path = "name",params = "nameFilter",spec = EqualIgnoreCase.class)Specification<User> spec) {
        log.info("Called by " + username);
        return service.list(spec);
    }
}
