package com.farmpro.application.enums;

public enum UserRole {
    ADMIN("admin"),
    MANAGER("manager"),
    EMPLOYEE("employee");

    private final String roleName;

    UserRole(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
