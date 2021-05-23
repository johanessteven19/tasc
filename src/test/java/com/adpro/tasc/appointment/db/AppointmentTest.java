package com.adpro.tasc.appointment.db;

import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.Course;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {
    @Test
    void testAppointmentExists() throws Exception {
        Course course = new Course();
        course.setName("A");
        Appointment appointment = new Appointment();
        appointment.setDate(19-10-21);
        appointment.setDuration(20);
        appointment.setCourse(course);
        assertEquals(19-10-21, appointment.getDate());
        assertEquals(20, appointment.getDuration());
        assertEquals(course, appointment.getCourse());
    }
}
