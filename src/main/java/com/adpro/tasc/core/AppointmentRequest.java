package com.adpro.tasc.core;

import java.util.Date;

public class AppointmentRequest {

    private Appointment appointment;
    private Student student;
    private TeachingAssistant ta;
    private Date requestTime;
    private String status;
    private boolean adminHasPermission;
    private Reminder[] reminders;

    int index = 0;

    public String getStudent() {
        return student.fullName;
    }

    public String getTa() {
        return ta.fullName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdminHasPermission(boolean adminHasPermission) {
        this.adminHasPermission = adminHasPermission;
    }

    public boolean getAdminHasPermission() {
        return adminHasPermission;
    }

    public void addReminder(Reminder reminder) {
       while(reminders[index] != null) {
           reminders[index] = reminder;
           index++;
       }
       index++;
    }

    public void deleteReminder(Reminder reminder) {
        reminders[index] = null;
    }

}
