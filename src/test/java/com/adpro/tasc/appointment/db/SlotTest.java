package com.adpro.tasc.appointment.db;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotTest {

    @Test
    void testSlotsId() throws Exception {
        Slot slot = new Slot();
        slot.setId(1);
        assertEquals(1, slot.getId());
    }

    @Test
    void testSlotsSchedule() throws Exception {
        Slot slot = new Slot();
        slot.setSchedule(1);
        assertEquals(1, slot.getSchedule());
    }

    @Test
    void testSlotsStartTime() throws Exception {
        Slot slot = new Slot();
        slot.setStartTime(1);
        assertEquals(1, slot.getStartTime());
    }

    @Test
    void testSlotsEndTime() throws Exception {
        Slot slot = new Slot();
        slot.setFinishTime(1);
        assertEquals(1, slot.getFinishTime());
    }

    @Test
    void testSlotsDay() throws Exception {
        Slot slot = new Slot();
        slot.setDay(Slot.Day.TUESDAY);
        assertEquals(Slot.Day.TUESDAY, slot.getDay());
    }

}
