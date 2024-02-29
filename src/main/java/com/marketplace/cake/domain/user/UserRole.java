package com.marketplace.cake.domain.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
