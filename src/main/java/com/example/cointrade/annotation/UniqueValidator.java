package com.example.cointrade.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class UniqueValidator implements ConstraintValidator<Unique,String> {

    @Autowired
    private ApplicationContext context;

    private String methodName;

    private Object repository;

    public UniqueValidator() {
    }


    @Override
    public void initialize(Unique constraint) {
        this.repository=  this.context.getBean(constraint.repositoryClass());
        this.methodName=constraint.methodName();
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        try{
            return !(boolean)this.repository.getClass().getMethod(this.methodName, String.class)
                    .invoke(this.repository, value);
        }catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException var4){
            throw new RuntimeException(var4);
        }

    }
}

