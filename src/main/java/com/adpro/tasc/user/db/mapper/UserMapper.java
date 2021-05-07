package com.adpro.tasc.user.db.mapper;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    private CourseDAO courseDB;
    private ScheduleDAO scheduleDB;

    public UserMapper(CourseDAO courseDB, ScheduleDAO scheduleDB) {
        this.courseDB = courseDB;
        this.scheduleDB = scheduleDB;
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(
                rs.getString("u.username"),
                rs.getString("u.full_name"),
                rs.getString("u.password"),
                Role.valueOf(rs.getString("u.role"))
        );

        return updateByRole(user);
    }

    private User updateByRole(User user) {
        if(!Role.ADMIN.equals(user.getRole())) {
            AcademicUser academicUser = new AcademicUser(user);

            academicUser.setCourses(courseDB.getUserCourseList(academicUser));
            academicUser.setSchedule(scheduleDB.getUserSchedule(academicUser));

            return academicUser;
        }

        return user;
    }
}
