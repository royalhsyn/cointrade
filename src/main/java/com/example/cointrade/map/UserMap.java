package com.example.cointrade.map;

import com.example.cointrade.dto.RegistrationDto;
import com.example.cointrade.dto.UserDto;
import com.example.cointrade.entity.Transaction;
import com.example.cointrade.entity.User;
import com.example.cointrade.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(imports = UUID.class)
public abstract class UserMap {

    @Lazy
    @Autowired
    protected PasswordEncoder encoder;

    @Mapping(target = "walletId", expression = "java(toSet(user.getWallets()))")
    @Mapping(target = "transactionId", expression = "java(toSetTransaction(user.getTransactions()))")
    public abstract UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    public abstract User toEntity(UserDto userDto);

    @Mapping(target = "email", source = "username")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName",ignore = true)
    @Mapping(target = "birthdate",ignore = true)
    @Mapping(target = "balance",ignore = true)
    @Mapping(target = "enabled", constant = "false")
    @Mapping(target = "credentialsNonExpired", constant = "true")
    @Mapping(target = "accountNonLocked", constant = "true")
    @Mapping(target = "accountNonExpired", constant = "true")
    @Mapping(target = "password", expression = "java(encoder.encode(dto.getPassword()))")
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    public abstract User toEntity(RegistrationDto dto);


    public List<Long> toSetTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getId)
                .collect(Collectors.toList());
    }

    public List<Long> toSet(List<Wallet> wallets) {
        return wallets.stream()
                .map(Wallet::getId)
                .collect(Collectors.toList());
    }
}
