package com.adpro.tasc.appointment.db.dao;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.user.db.model.AcademicUser;

public interface ScheduleDAO {
    Schedule getUserSchedule(AcademicUser user);
    Schedule getUserScheduleByDay(AcademicUser user, Slot.Day day);
}
