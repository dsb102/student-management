package com.example.quanlisinhvien.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimesheetValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface TodayOrFuture {
    String message() default "Lịch làm đăng ký phải là hôm nay hoặc tương lai";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
