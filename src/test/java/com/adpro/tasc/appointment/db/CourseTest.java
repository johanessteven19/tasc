package com.adpro.tasc.appointment.db;

import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseTest {
    @Test
    void testCourseExists() throws Exception {
        Course course = new Course();
        course.setName("Course A");
        assertEquals("Course A", course.getName());
    }
}
