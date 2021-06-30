package com.adpro.tasc.user.db;

import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.Role;
import com.adpro.tasc.user.db.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private MockMvc mockMvc;

//    @Test
//    void testUserNameExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        assertEquals("Test", user.getUserName());
//    }
//
//    @Test
//    void testFullNameExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        assertEquals("test", user.getFullName());
//    }
//
//    @Test
//    void testPasswordExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        assertEquals("123", user.getPassword());
//    }
//
//    @Test
//    void testRoleExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        assertEquals(Role.ROLE_STUDENT, user.getRole());
//    }
//
//    @Test
//    void testChangeUserNameWorks() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        user.setUserName("Changed");
//        assertEquals("Changed", user.getUserName());
//    }
//
//    @Test
//    void testChangePasswordWorks() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        user.setPassword("ChangedPass");
//        assertEquals("ChangedPass", user.getPassword());
//    }
//
//    @Test
//    void testChangeRoleWorks() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_STUDENT);
//        user.setRole(Role.ROLE_TEACHING_ASSISTANT);
//        assertEquals(Role.ROLE_TEACHING_ASSISTANT, user.getRole());
//    }
//
    @Test
    void testSidAssigned() throws Exception {
        User user = new User("Test", "test", "123", Role.ROLE_STUDENT, 0);
        assertEquals(0, user.getSid());
    }

//    @Test
//    public void loginWithStudentTest() throws Exception {
//        this.mockMvc
//                .perform(get("/home-student")
//                        .with(user("student").password("{noop}123").roles("STUDENT")))
//                .andDo(print()).andExpect(status().isOk());
//    }



}
