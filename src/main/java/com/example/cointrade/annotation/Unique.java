package com.example.cointrade.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {UniqueValidator.class}
)
@Documented
public @interface Unique {

    Class<?> repositoryClass();

    String methodName();

    String message() default "{company.registration.annotation.Unique.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

