package com.adpro.tasc.appointment.db.dao;

import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.model.User;

import java.util.List;

public interface AppointmentDAO {
    List<AppointmentRequest> getAllAppointment();
    List<AppointmentRequest> getAppointmentByCourse(Course course);
    List<AppointmentRequest> getAppointmentByUser(User user);

    void createAppointment(AppointmentRequest request);

    void updateStatus(AppointmentRequest request, AppointmentRequest.Status status);
}
