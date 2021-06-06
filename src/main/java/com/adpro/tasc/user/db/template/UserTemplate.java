package com.adpro.tasc.user.db.template;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.mapper.UserMapper;
import com.adpro.tasc.user.db.model.AcademicUser;
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

    private User updateByRole(User user) {
        if(!Role.ROLE_ADMIN.equals(user.getRole())) {
            AcademicUser academicUser = new AcademicUser(user);

            academicUser.setCourses(courseDB.getUserCourseList(academicUser));
            academicUser.setSchedule(scheduleDB.getUserSchedule(academicUser));

            return academicUser;
        }

        return user;
    }

    private List<User> updateByRole(List<User> users) {
        for(int i = 0; i < users.size(); i++) {
            users.set(i, updateByRole(users.get(i)));
        }

        return users;
    }

    @Override
    public User getUser(String username) {
        String sql = """
            select *
            from "user"
            where username = ?
        """;

        return updateByRole(template.queryForObject(sql, new UserMapper(), username));
    }

    @Override
    public List<User> getAllUser() {
        String sql = """
                select *
                from "user"
                """;

        return updateByRole(template.query(sql, new UserMapper()));
    }

    @Override
    public List<User> getTAbyCourse(Course course) {
        String sql = """
                select u.*
                from course_list c
                inner join "user" u on u.username = c.username
                where u.role=? and c.course=?
                """;

        return updateByRole(template.query(sql, new UserMapper(),
                Role.ROLE_TEACHING_ASSISTANT.toString(), course.getName()));
    }

    @Override
    public void createUser(User user) {
        String sql = """
                insert into "user" (username, full_name, password, role)
                values (?, ?, ?, ?)
                """;

        template.update(sql,
                user.getUserName(), user.getFullName(), user.getPassword(), user.getRole().toString());
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
