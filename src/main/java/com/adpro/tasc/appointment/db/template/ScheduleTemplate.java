package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.dao.SlotDAO;
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
    private SlotDAO slotDB;

    @Autowired
    public void setSlotDB(SlotDAO slotDB) {
        this.slotDB = slotDB;
    }

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

        List<Slot> slots = slotDB.getAll(schedule);
        schedule.setAvailableSlots(slots);

        return schedule;
    }

    @Override
    public Schedule getUserScheduleByDay(AcademicUser user, Slot.Day day) {
        String sql = """
                select *
                from schedule
                where schedule.user=?
                """;
        Schedule schedule = template.queryForObject(sql, new ScheduleMapper(userDB), user.getUserName());

        List<Slot> slots = slotDB.getByDay(schedule, day);
        schedule.setAvailableSlots(slots);

        return schedule;
    }
}
