package com.BlogNation.br.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String getRole;

    UserRole(String role) {
        this.getRole = role;
    }
}
