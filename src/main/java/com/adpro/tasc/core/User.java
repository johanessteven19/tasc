package com.adpro.tasc.core;

public class User {
    protected String userName;
    protected String fullName;
    protected String role;

    public User(String userName, String fullName, String role) {
        this.userName = userName;
        this.fullName = fullName;
        this.role = role;
    }

    public void setUserName() {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFullName() {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

}
