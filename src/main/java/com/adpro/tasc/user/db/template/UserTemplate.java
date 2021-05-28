package com.adpro.tasc.user.db.template;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.mapper.UserMapper;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTemplate implements UserDAO {
    private JdbcTemplate template;
    private CourseDAO courseDB;
    private ScheduleDAO scheduleDB;

    @Autowired
    public void setScheduleDB(ScheduleDAO scheduleDB) {
        this.scheduleDB = scheduleDB;
    }

    @Autowired
    public void setCourseDB(CourseDAO courseDB) {
        this.courseDB = courseDB;
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public User getUser(String username) {
        String sql = """
            select *
            from "user"
            where username = ?
        """;

        return template.queryForObject(sql, new UserMapper(courseDB, scheduleDB), username);
    }

    @Override
    public List<User> getAllUser() {
        String sql = """
                select *
                from "user"
                """;

        return template.query(sql, new UserMapper(courseDB, scheduleDB));
    }

    @Override
    public List<User> getTAbyCourse(Course course) {
        String sql = """
                select u.*
                from course_list c
                inner join "user" u on u.username = c.username
                where u.role=? and c.course=?
                """;

        return template.query(sql, new UserMapper(courseDB, scheduleDB),
                Role.ROLE_TEACHING_ASSISTANT.toString(), course.getName());
    }

    @Override
    public void createUser(User user) {
        String sql = """
                insert into "user" (username, full_name, password, role)
                values (?, ?, ?, ?)
                """;

        template.update(sql,
                user.getUserName().toString(), user.getFullName().toString(), user.getPassword().toString(), user.getRole().toString());
    }

    @Override
    public void updatePassword(String username, String password) {
        String sql = """
                update "user"
                set password=?
                where username=?
                """;

        template.update(sql, password, username);
    }

    @Override
    public void updateUser(String username, User user) {
        String sql = """
                update "user"
                set username=?, full_name=?, role=?
                where username=?
                """;

        template.update(sql,
                user.getUserName(), user.getFullName(), user.getRole().toString(),
                username);
    }

    @Override
    public void deleteUser(String username) {
        String sql = """
                delete from "user"
                where username=?
                """;

        template.update(sql, username);
    }
}
