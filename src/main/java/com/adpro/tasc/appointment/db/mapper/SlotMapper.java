package com.adpro.tasc.appointment.db.mapper;

import com.adpro.tasc.appointment.db.model.Slot;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SlotMapper implements RowMapper<Slot> {
    @Override
    public Slot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Slot slot = new Slot();

        slot.setId(rs.getInt("slot.id"));
        slot.setSchedule(rs.getInt("slot.schedule"));
        slot.setStartTime(rs.getLong("slot.start_time"));
        slot.setFinishTime(rs.getLong("slot.finish_time"));
        slot.setDay(Slot.Day.valueOf(rs.getString("slot.day")));

        return slot;
    }
}
