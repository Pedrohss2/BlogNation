package com.BlogNation.br.model.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String getRole;

    UserRole(String role) {
        this.getRole = role;
    }
}
