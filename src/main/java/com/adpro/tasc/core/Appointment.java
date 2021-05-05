package com.adpro.tasc.core;

import java.util.Date;

public class Appointment {
    private Date date;
    private double duration;
    private Course course;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourse() {
        return course.getCourseName();
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
