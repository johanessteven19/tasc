package com.adpro.tasc.appointment.db.model;

import com.adpro.tasc.user.db.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    private int id;
    private User user;
    private List<Slot> availableSlots;
    private boolean adminHasPermission;
}
