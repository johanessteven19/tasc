package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();

        String name;
        try {
            name = rs.getString("name");
        } catch (SQLException e) {
            name = rs.getString("course");
        }

        course.setName(name);

        return course;
    }
}
