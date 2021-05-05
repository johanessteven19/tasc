package com.adpro.tasc.core;

public abstract class Academic extends User{
    public Academic(String userName, String fullName, String role) {
        super(userName, fullName, role);
    }

    protected String course;
    protected String schedule;

    public String getCourse() {
        return course;
    }

    public String getSchedule() {
        return schedule;
    }

}
