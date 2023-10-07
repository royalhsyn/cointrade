package com.example.cointrade.service;

import com.example.cointrade.dto.UserDto;
import com.example.cointrade.entity.User;
import com.example.cointrade.exception.NotFoundException;
import com.example.cointrade.map.UserMap;
import com.example.cointrade.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final UserMap map;


    public UserService(UserRepo userRepo, UserMap map) {
        this.userRepo = userRepo;
        this.map = map;
    }

    public List<UserDto> list (Specification<User> spec){
        return userRepo.findAll(spec)
                .stream().map(map::toDto)
                .toList();
    }


//    public List<UserDto> findAll(){
//       return userRepo.findAll()
//                .stream()
//                .map(map :: toDto)
//                .collect(Collectors.toList());
//    }

    public UserDto findById(Long id){
        return userRepo
                .findById(id)
                .map(map::toDto)
                .orElseThrow(()->new NotFoundException("Bele bir id li user yoxdur"));
    }

    @Transactional
    public UserDto save(UserDto userDto){
        return Optional.of(userDto)
                .map(map::toEntity)
                .map(userRepo::save)
                .map(map::toDto)
                .orElseThrow();


    }

    @Transactional
    public void update(Long id, UserDto userDto) {

    }

    @Transactional
    public void delete(Long id){
        if(userRepo.existsById(id)) {
            try {
                userRepo.deleteById(id);

            } catch (RuntimeException ex) {
                throw new RuntimeException("Delete zamani xeta bas verdi");
            }
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElse(null);
    }

    public void increaseAttemptCount(String username) {
        userRepo.findByUsername(username)
                .ifPresent(user -> {
                    int attemptCount = user.getAttemptCount();
                    if (attemptCount > 2) {
                        user.setAccountNonLocked(false);
                    }
                    user.setAttemptCount(attemptCount + 1);

                    userRepo.save(user);
                });
    }

    public void resetAttempts(String username) {
        userRepo.findByUsername(username)
                .ifPresent(user -> {
                    user.setAttemptCount(0);
                    userRepo.save(user);
                });
    }
}
