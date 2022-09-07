package com.example.quanlisinhvien.model.eenum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_INTERNSHIP,
    ROLE_MENTOR,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
