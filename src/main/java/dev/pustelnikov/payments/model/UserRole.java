package dev.pustelnikov.payments.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CUSTOMER;

    @Override
    public String getAuthority() {
        return name();
    }
}
