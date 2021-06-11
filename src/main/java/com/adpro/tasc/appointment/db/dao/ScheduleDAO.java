package com.adpro.tasc.appointment.db.dao;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.User;

public interface ScheduleDAO {
    Schedule getUserSchedule(AcademicUser user);
    Schedule getUserScheduleByDay(AcademicUser user, Slot.Day day);

    void createSchedule(Schedule schedule);
    void deleteSchedule(int id);
    void deleteSchedule(User user);
}
