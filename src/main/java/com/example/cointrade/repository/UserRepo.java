package com.example.cointrade.repository;

import com.example.cointrade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepositoryImplementation<User,Long> {

    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);
}
