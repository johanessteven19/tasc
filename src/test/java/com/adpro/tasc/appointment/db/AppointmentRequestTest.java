package com.adpro.tasc.appointment.db;

import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentRequestTest {

    @Test
    void testAppointmentRequestId() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setId(1);
        assertEquals(1, appointmentRequest.getId());
    }

    @Test
    void testAppointmentRequestAppointment() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        Appointment appointment = new Appointment();
        appointmentRequest.setAppointment(appointment);
        assertEquals(appointment, appointmentRequest.getAppointment());
    }

    @Test
    void testAppointmentRequestStudent() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        User student = new User("Test", "test", "123", Role.ROLE_STUDENT);
        appointmentRequest.setStudent(student);
        assertEquals(student, appointmentRequest.getStudent());
    }

    @Test
    void testAppointmentRequestTA() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        User ta = new User("Test", "test", "123", Role.ROLE_TEACHING_ASSISTANT);
        appointmentRequest.setTa(ta);
        assertEquals(ta, appointmentRequest.getTa());
    }

    @Test
    void testAppointmentRequestTime() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setRequestTime(12);
        assertEquals(12, appointmentRequest.getRequestTime());
    }

    @Test
    void testAppointmentRequestStatus() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setStatus(AppointmentRequest.Status.ACCEPTED);
        assertEquals(AppointmentRequest.Status.ACCEPTED, appointmentRequest.getStatus());
    }

    @Test
    void testAppointmentRequestPermission() throws Exception {
        AppointmentRequest appointmentRequest = new AppointmentRequest();
        appointmentRequest.setAdminHasPermission(false);
        assertEquals(false, appointmentRequest.isAdminHasPermission());
    }

}
