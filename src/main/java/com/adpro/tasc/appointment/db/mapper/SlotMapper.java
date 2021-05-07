package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Slot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SlotMapper implements RowMapper<Slot> {
    @Override
    public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Slot slot = new Slot();

        slot.setId(rs.getInt("sl.id"));
        slot.setSchedule(rs.getInt("sl.schedule"));
        slot.setStartTime(rs.getLong("sl.start_time"));
        slot.setFinishTime(rs.getLong("sl.finish_time"));
        slot.setDay(Slot.Day.valueOf(rs.getString("sl.day")));

        return slot;
    }
}
