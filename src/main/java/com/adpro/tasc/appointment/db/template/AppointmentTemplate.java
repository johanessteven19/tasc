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

    private AppointmentRequest updateQuery(AppointmentRequest request) {
        User student = userDB.getUser(request.getStudent().getUserName());
        request.setStudent(student);

        User ta = userDB.getUser(request.getTa().getUserName());
        request.setTa(ta);

        return request;
    }

    private List<AppointmentRequest> updateQuery(List<AppointmentRequest> request) {
        for(int i = 0; i < request.size(); i++) {
            request.set(i, updateQuery(request.get(i)));
        }

        return request;
    }

    @Override
    public List<AppointmentRequest> getAllAppointment() {
        String sql = """
                select *
                from appointment_request
                """;

        return updateQuery(template.query(sql, new AppointmentMapper()));
    }

    @Override
    public List<AppointmentRequest> getAppointmentByCourse(Course course) {
        String sql = """
                select *
                from appointment_request
                where course=?
                """;

        return updateQuery(template.query(sql, new AppointmentMapper(), course.getName()));
    }

    @Override
    public List<AppointmentRequest> getAppointmentByUser(User user) {
        String sql = """
                select *
                from appointment_request
                where student=?
                """;

        List<AppointmentRequest> requests = template.query(sql, new AppointmentMapper(), user.getUserName());
        if(requests.isEmpty()) {
            sql = """
            select *
            from appointment_request
            where ta=?
            """;

            requests = template.query(sql, new AppointmentMapper(), user.getUserName());
        }

        return updateQuery(requests);
    }

    @Override
    public void createAppointment(AppointmentRequest request) {
        String sql = """
                insert into appointment_request (student, ta, request_time, date, duration, course) 
                values (?, ?, ?, ?, ?, ?)
                """;

        Appointment appointment = request.getAppointment();
        template.update(sql,
                request.getStudent().getUserName(), request.getTa().getUserName(), request.getRequestTime(),
                appointment.getDate(), appointment.getDuration(), appointment.getCourse().getName());
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

    @Override
    public void updateAdminHasPermission(AppointmentRequest request, boolean permission) {
        String sql = """
                update appointment_request
                set admin_permission=?
                where id=?
                """;

        template.update(sql, permission, request.getId());
    }
}
