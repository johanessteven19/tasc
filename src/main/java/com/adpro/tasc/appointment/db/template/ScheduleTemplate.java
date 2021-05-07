package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.mapper.ScheduleMapper;
import com.adpro.tasc.appointment.db.mapper.SlotMapper;
import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTemplate implements ScheduleDAO {
    private JdbcTemplate template;
    private UserDAO userDB;

    @Autowired
    public void setUserDB(UserDAO userDB) {
        this.userDB = userDB;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Schedule getUserSchedule(AcademicUser user) {
        String sql = """
                select *
                from schedule
                where schedule.user=?
                """;

        Schedule schedule = template.queryForObject(sql, new ScheduleMapper(userDB), user.getUserName());

        sql = """
            select *
            from slot
            where schedule=?
            """;
        List<Slot> slots = template.query(sql, new SlotMapper(), schedule.getId());
        schedule.setAvailableSlots(slots);

        return schedule;
    }

    @Override
    public void addUserScheduleSlot(Slot slot) {
        String sql = """
                insert into slot (schedule, start_time, finish_time, day)
                values (?, ?, ?, ?)
                """;

        template.update(sql,
                slot.getSchedule(), slot.getStartTime(), slot.getFinishTime(), slot.getDay().toString());
    }

    @Override
    public void deleteUserScheduleSlot(Slot slot) {
        String sql = """
                delete from slot
                where id=?
                """;

        template.update(sql, slot.getId());
    }
}
