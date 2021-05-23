package com.adpro.tasc.user.db.model;

public enum Role {
        ADMIN,
        STUDENT,
        TEACHING_ASSISTANT;

    public String toString() {
        return switch (this) {
            case ADMIN -> "Admin";
            case STUDENT -> "Student";
            case TEACHING_ASSISTANT -> "Teaching Assistant";
            default -> "No roles";
        };
    };

}
