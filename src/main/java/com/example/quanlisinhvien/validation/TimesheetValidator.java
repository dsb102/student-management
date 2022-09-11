package com.example.quanlisinhvien.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class TimesheetValidator implements ConstraintValidator<TodayOrFuture, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return localDate.isAfter(LocalDate.now()) || localDate.equals(LocalDate.now());
    }
}
