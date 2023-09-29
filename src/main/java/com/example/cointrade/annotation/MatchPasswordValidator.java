package com.example.cointrade.annotation;

import com.example.cointrade.dto.PasswordDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, PasswordDto> {
    @Override
    public boolean isValid(PasswordDto value, ConstraintValidatorContext constraintValidatorContext) {
        return value==null|| Objects.equals(value.getPassword(), value.getConfirmPassword());
    }
}
