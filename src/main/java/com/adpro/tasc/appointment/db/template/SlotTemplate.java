package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.SlotDAO;
import com.adpro.tasc.appointment.db.mapper.SlotMapper;
import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotTemplate implements SlotDAO {
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Slot> getAll(Schedule schedule) {
        String sql = """
            select *
            from slot
            where schedule=?
            """;

        return template.query(sql, new SlotMapper(), schedule.getId());
    }

    @Override
    public List<Slot> getByDay(Schedule schedule, Slot.Day day) {
        String sql = """
            select *
            from slot
            where schedule=? and day=?
            """;

        return template.query(sql, new SlotMapper(), schedule.getId(), day.toString());
    }

    @Override
    public void addSlot(Slot slot) {
        String sql = """
                insert into slot (schedule, start_time, finish_time, day)
                values (?, ?, ?, ?)
                """;

        template.update(sql,
                slot.getSchedule(), slot.getStartTime(), slot.getFinishTime(), slot.getDay().toString());
    }

    @Override
    public void deleteSlot(Slot slot) {
        String sql = """
                delete from slot
                where id=?
                """;

        template.update(sql, slot.getId());
    }
}
