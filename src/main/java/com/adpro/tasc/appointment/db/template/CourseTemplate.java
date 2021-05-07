package com.adpro.tasc.appointment.db.template;

import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.mapper.CourseMapper;
import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.model.AcademicUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseTemplate implements CourseDAO {
    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Course> getUserCourseList(AcademicUser user) {
        String sql = """
                select *
                from course_list
                where username=?
                """;

        return template.query(sql, new CourseMapper(), user.getUserName());
    }

    @Override
    public void addUserCourse(AcademicUser user, Course course) {
        String sql = """
                insert into course_list (username, course)
                values (?, ?)
                """;

        template.update(sql, user.getUserName(), course.getName());
    }

    @Override
    public void deleteUserCourse(AcademicUser user, Course course) {
        String sql = """
                delete from course_list
                where username=? and course=?
                """;

        template.update(sql, user.getUserName(), course.getName());
    }

    @Override
    public List<Course> getAllCourse() {
        String sql = """
                select *
                from course
                """;

        return template.query(sql, new CourseMapper());
    }

    @Override
    public void updateCourse(String name, Course course) {
        String sql = """
                update course
                set name=?
                where name=?
                """;

        template.update(sql, course.getName(), name);
    }

    @Override
    public void deleteCourse(Course course) {
        String sql = """
                delete from course
                where name=?
                """;

        template.update(sql, course.getName());
    }
}
