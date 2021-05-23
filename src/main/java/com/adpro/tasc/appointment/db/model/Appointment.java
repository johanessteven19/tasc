package com.adpro.tasc.appointment.db.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Appointment {
    private long date;
    private long duration;
    private Course course;
}
