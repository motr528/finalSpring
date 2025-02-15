package com.kek.finalSpring.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, SPEAKER;


    @Override
    public String getAuthority() {
        return name();
    }
}