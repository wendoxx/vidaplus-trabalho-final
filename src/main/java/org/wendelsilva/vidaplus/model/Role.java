package org.wendelsilva.vidaplus.model;

public enum Role {
    USER("user"),
    ADMIN("admin"),
    MEDICO("medico");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
