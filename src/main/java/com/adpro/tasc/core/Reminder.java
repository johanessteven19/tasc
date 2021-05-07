package com.adpro.tasc.core;

import java.util.Date;

public class Reminder {

    private User user;
    private AppointmentRequest appointmentRequest;
    private Date remindOn;

    public String remind(Date remindOn) {
        Date currentDate = new Date();
        if (currentDate == remindOn) {
            return "Reminder here!!";
        }
        else return "No reminders";
    }
}
