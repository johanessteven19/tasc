package com.adpro.tasc.appointment.db.dao;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;

import java.util.List;

public interface SlotDAO {
    List<Slot> getAll(Schedule schedule);
    List<Slot> getByDay(Schedule schedule, Slot.Day day);

    void addSlot(Slot slot);
    void deleteSlot(Slot slot);
}
