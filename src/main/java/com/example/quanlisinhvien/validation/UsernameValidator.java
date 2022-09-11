package com.example.quanlisinhvien.validation;

import com.example.quanlisinhvien.model.User;
import com.example.quanlisinhvien.serivce.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<UsernameConstraint, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UsernameConstraint usernameConstraint) {
    }

    @Override
    public boolean isValid(String usernameField, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findUserByUsername(usernameField) == null;
    }
}
