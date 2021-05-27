package com.adpro.tasc.appointment.db.dao;

import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.model.AcademicUser;

import java.util.List;

public interface CourseDAO {
    List<Course> getUserCourseList(AcademicUser user);

    void addUserCourse(AcademicUser user, Course course);
    void deleteUserCourse(AcademicUser user, Course course);

    List<Course> getAllCourse();
    
    void updateCourse(String name, Course course);
    void deleteCourse(Course course);

    void createCourse(Course course);
}
