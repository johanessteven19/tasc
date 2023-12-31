package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<AppointmentRequest> {
    @Override
    public AppointmentRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppointmentRequest request = new AppointmentRequest();

        request.setId(rs.getInt("id"));
        request.setStudent(new User(rs.getString("student"), null, null, null, 0));
        request.setTa(new User(rs.getString("ta"), null, null, null, 0));
        request.setRequestTime(rs.getLong("request_time"));
        request.setStatus(AppointmentRequest.Status.valueOf(rs.getString("status")));
        request.setAdminHasPermission(rs.getBoolean("admin_permission"));

        Appointment appointment = new Appointment();

        Course course = new Course();
        course.setName(rs.getString("course"));
        appointment.setCourse(course);

        appointment.setDate(rs.getLong("date"));
        appointment.setDuration(rs.getLong("duration"));

        request.setAppointment(appointment);

        return request;
    }
}
