package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.user.db.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setUser(new User(rs.getString("user"), null, null, null, 0));
        schedule.setAdminHasPermission(rs.getBoolean("admin_permission"));

        return schedule;
    }
}
