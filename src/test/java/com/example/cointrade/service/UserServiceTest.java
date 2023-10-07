package com.example.cointrade.service;

import com.example.cointrade.dto.UserDto;
import com.example.cointrade.entity.User;
import com.example.cointrade.map.UserMap;
import com.example.cointrade.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserService service;

    @Mock
    private UserMap map;

    @Mock
    private UserRepo userRepo;

    @Mock
    private MockMvc mockMvc;

    @Test
    void saveAndExceptOk() {
        UserDto dto=new UserDto();
        dto.setFirstName("u1");
        dto.setLastName("n1");
        dto.setEmail("aa@bb.cc");
        UserDto createdUserDto = service.save(dto);

        User user=User.builder()
                        .firstName("u1")
                                .lastName("n1")
                                        .email("aa@bb.cc").build();

    when(map.toEntity(createdUserDto)).thenReturn(user);


        assertNotNull(createdUserDto);
        assertNotNull(createdUserDto.getId());
        assertTrue(createdUserDto.getId() > 0);
    }

    @Test
    void save() {
    }
}