package com.adpro.tasc.user.db;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserTest {

   private UserDAO userDAO;

    @Test
    void testUserNameExists() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        assertEquals("Test", user.getUserName());
    }

    @Test
    void testFullNameExists() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        assertEquals("test", user.getFullName());
    }

    @Test
    void testPasswordExists() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        assertEquals("123", user.getPassword());
    }

    @Test
    void testRoleExists() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        assertEquals(Role.ROLE_STUDENT, user.getRole());
    }

    @Test
    void testChangeUserNameWorks() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        user.setUserName("Changed");
        assertEquals("Changed", user.getUserName());
    }

    @Test
    void testChangePasswordWorks() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        user.setPassword("ChangedPass");
        assertEquals("ChangedPass", user.getPassword());
    }

    @Test
    void testChangeRoleWorks() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        user.setRole(Role.ROLE_TEACHING_ASSISTANT);
        assertEquals(Role.ROLE_TEACHING_ASSISTANT, user.getRole());
    }

    @Test
    void testChangeFullNameWorks() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
        user.setFullName("FullName");
        assertEquals("FullName", user.getFullName());
    }

}
