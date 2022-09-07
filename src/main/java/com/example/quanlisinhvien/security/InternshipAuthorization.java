package com.example.quanlisinhvien.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasRole(T(com.example.quanlisinhvien.model.eenum.Role).ROLE_INTERNSHIP)")
public @interface InternshipAuthorization {
}
