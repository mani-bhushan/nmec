package com.apps.nmec.enums;

public enum ERole {
    ADMIN ("ADMIN"),
    COUNSELOR("COUNSELOR"),
    CANDIDATE("CANDIDATE"),
    STAFF ("STAFF"),
    USER ("USER");

    private final String roleAction;

    // getter method
    public String getRoleAction() {
        return this.roleAction;
    }

    // enum constructor - cannot be public or protected
    ERole(String roleAction) {
        this.roleAction = roleAction;
    }
}
