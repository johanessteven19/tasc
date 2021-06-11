package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Slot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SlotMapper implements RowMapper<Slot> {
    @Override
    public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Slot slot = new Slot();

        slot.setId(rs.getInt("id"));
        slot.setSchedule(rs.getInt("schedule"));
        slot.setStartTime(rs.getLong("start_time"));
        slot.setFinishTime(rs.getLong("finish_time"));
        slot.setDay(Slot.Day.valueOf(rs.getString("day")));

        return slot;
    }
}
