package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.appointment.db.mapper.AppointmentMapper;
import com.adpro.tasc.appointment.db.model.Appointment;
import com.adpro.tasc.appointment.db.model.AppointmentRequest;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentTemplate implements AppointmentDAO {
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
    public List<AppointmentRequest> getAllAppointment() {
        String sql = """
                select *
                from appointment_request
                """;

        return template.query(sql, new AppointmentMapper(userDB));
    }

    @Override
    public List<AppointmentRequest> getAppointmentByCourse(Course course) {
        String sql = """
                select *
                from appointment_request
                where course=?
                """;

        return template.query(sql, new AppointmentMapper(userDB), course.getName());
    }

    @Override
    public List<AppointmentRequest> getAppointmentByUser(User user) {
        String sql = """
                select *
                from appointment_request
                where student=?
                """;

        List<AppointmentRequest> requests = template.query(sql, new AppointmentMapper(userDB));
        if(requests.isEmpty()) {
            sql = """
            select *
            from appointment_request
            where ta=?
            """;

            requests = template.query(sql, new AppointmentMapper(userDB));
        }

        return requests;
    }

    @Override
    public void createAppointment(AppointmentRequest request) {
        String sql = """
                insert into appointment_request (student, ta, request_time, date, duration, course) 
                values (?, ?, ?, ?, ?, ?)
                """;

        Appointment appointment = request.getAppointment();
        template.update(sql,
                request.getStudent(), request.getTa(), request.getRequestTime(),
                appointment.getDate(), appointment.getDuration(), appointment.getCourse());
    }

    @Override
    public void updateStatus(AppointmentRequest request, AppointmentRequest.Status status) {
        String sql = """
                update appointment_request
                set status=?
                where id=?
                """;

        template.update(sql, status.toString(), request.getId());
    }
}
