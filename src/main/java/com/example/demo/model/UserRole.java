package com.example.demo.model;

public enum UserRole {
    ADMIN(1),
    USER(2);


    private int code;

    UserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserRole valueOf(int code) {
        for (UserRole value : UserRole.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("role não encontrado");
    }
}
