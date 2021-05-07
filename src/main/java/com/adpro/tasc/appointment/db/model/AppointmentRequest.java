package com.adpro.tasc.appointment.db.model;

import com.adpro.tasc.user.db.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppointmentRequest {
    public enum Status {
        ACCEPTED,
        REJECTED,
        PENDING
    }

    private int id;
    private Appointment appointment;
    private User student;
    private User ta;
    private long requestTime;
    private Status status;
    private boolean adminHasPermission;
}
