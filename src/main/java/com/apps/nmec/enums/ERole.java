package com.apps.nmec.enums;

public enum ERole {
    ADMIN ("ADMIN"),
    USER ("USER");

    private final String roleAction;

    // getter method
    public String getRoleAction() {
        return this.roleAction;
    }

    // enum constructor - cannot be public or protected
    private ERole(String roleAction) {
        this.roleAction = roleAction;
    }
}
