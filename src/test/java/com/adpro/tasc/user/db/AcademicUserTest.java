//package com.adpro.tasc.user.db;
//
//import com.adpro.tasc.appointment.db.model.Course;
//import com.adpro.tasc.appointment.db.model.Schedule;
//import com.adpro.tasc.user.db.model.AcademicUser;
//import com.adpro.tasc.user.db.model.Role;
//import com.adpro.tasc.user.db.model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AcademicUserTest {
//
//    @Test
//    void testAcademicUserNameExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_UNASSIGNED);
//        AcademicUser student = new AcademicUser(user);
//        student.setRole(Role.ROLE_STUDENT);
//        assertEquals("Test", student.getUserName());
//    }
//
//    @Test
//    void testAcademicUserFullNameExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_UNASSIGNED);
//        AcademicUser student = new AcademicUser(user);
//        student.setRole(Role.ROLE_STUDENT);
//        assertEquals("test", student.getFullName());
//    }
//
//    @Test
//    void testAcademicUserPasswordExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_UNASSIGNED);
//        AcademicUser student = new AcademicUser(user);
//        student.setRole(Role.ROLE_STUDENT);
//        assertEquals("123", student.getPassword());
//    }
//
//    @Test
//    void testAcademicUserRoleExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_UNASSIGNED);
//        AcademicUser student = new AcademicUser(user);
//        student.setRole(Role.ROLE_STUDENT);
//        assertEquals(Role.ROLE_STUDENT, student.getRole());
//    }
//
//    @Test
//    void testSetAcademicUserName() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        student.setUserName("Changed");
//        assertEquals("Changed", student.getUserName());
//    }
//
//    @Test
//    void testSetAcademicUserFullName() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        student.setFullName("changed");
//        assertEquals("changed", student.getFullName());
//    }
//
//    @Test
//    void testSetAcademicUserPassword() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        student.setPassword("pass");
//        assertEquals("pass", student.getPassword());
//    }
//
//    @Test
//    void testSetAcademicUserRole() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        student.setRole(Role.ROLE_TEACHING_ASSISTANT);
//        assertEquals(Role.ROLE_TEACHING_ASSISTANT, student.getRole());
//    }
//
//    @Test
//    void testSetAcademicUserCourses() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        Course course = new Course();
//        List<Course> courseList = new ArrayList<Course>();
//        courseList.add(course);
//        student.setCourses(courseList);
//        assertEquals(courseList, student.getCourses());
//    }
//
//    @Test
//    void testSetAcademicUserSchedule() throws Exception {
//        AcademicUser student = new AcademicUser("Test", "test", "123", Role.ROLE_STUDENT);
//        Schedule schedule = new Schedule();
//        student.setSchedule(schedule);
//        assertEquals(schedule, student.getSchedule());
//    }
//
//}
