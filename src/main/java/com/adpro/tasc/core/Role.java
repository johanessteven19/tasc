package com.adpro.tasc.core;

public enum Role {

        ADMIN,
        STUDENT,
        TEACHING_ASSISTANT;

    public String toString() {
        switch(this) {
            case ADMIN:
                return "Admin";
            case STUDENT:
                return "Student";
            case TEACHING_ASSISTANT:
                return "Teaching Assistant";
            default:
                return "No roles";
        }
    };

}
