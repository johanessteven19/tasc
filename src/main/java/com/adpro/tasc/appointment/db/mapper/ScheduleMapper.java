package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    private UserDAO userDB;

    public ScheduleMapper(UserDAO userDB) {
        this.userDB = userDB;
    }

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setUser(userDB.getUser(rs.getString("user")));
        schedule.setAdminHasPermission(rs.getBoolean("admin_permission"));

        return schedule;
    }
}
