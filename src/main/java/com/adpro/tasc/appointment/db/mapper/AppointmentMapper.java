package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements RowMapper<AppointmentRequest> {
    private UserDAO userDB;

    public AppointmentMapper(UserDAO userDB) {
        this.userDB = userDB;
    }

    @Override
    public AppointmentRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppointmentRequest request = new AppointmentRequest();

        request.setId(rs.getInt("id"));
        request.setStudent(userDB.getUser(rs.getString("student")));
        request.setTa(userDB.getUser(rs.getString("ta")));
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
