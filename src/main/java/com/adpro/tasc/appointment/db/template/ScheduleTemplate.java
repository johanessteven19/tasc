package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.dao.SlotDAO;
import com.adpro.tasc.appointment.db.mapper.ScheduleMapper;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleTemplate implements ScheduleDAO {
    private JdbcTemplate template;
    private SlotDAO slotDB;

    @Autowired
    public void setSlotDB(SlotDAO slotDB) {
        this.slotDB = slotDB;
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
        try {
            Schedule schedule = template.queryForObject(sql, new ScheduleMapper(), user.getUserName());

            schedule.setUser(user);

            List<Slot> slots = slotDB.getAll(schedule);
            schedule.setAvailableSlots(slots);

            return schedule;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Schedule getUserScheduleByDay(AcademicUser user, Slot.Day day) {
        String sql = """
                select *
                from schedule
                where schedule.user=?
                """;
        try {
            Schedule schedule = template.queryForObject(sql, new ScheduleMapper(), user.getUserName());

            schedule.setUser(user);

            List<Slot> slots = slotDB.getByDay(schedule, day);
            schedule.setAvailableSlots(slots);

            return schedule;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private void validateUser(User user) {
        if(!Role.ROLE_TEACHING_ASSISTANT.equals(user.getRole())) {
            throw new IllegalArgumentException("User must be Teaching Assistant");
        }
    }

    @Override
    public void createSchedule(Schedule schedule) {
        validateUser(schedule.getUser());

        String sql = """
                insert into schedule ("user")
                values (?)
                """;

        template.update(sql, schedule.getUser().getUserName());
    }

    @Override
    public void deleteSchedule(int id) {
        String sql = """
                delete from schedule
                where id=?
                """;

        template.update(sql, id);
    }

    @Override
    public void deleteSchedule(User user) {
        validateUser(user);

        String sql = """
                delete from schedule
                where "user"=?
                """;

        template.update(sql, user.getUserName());
    }
}
