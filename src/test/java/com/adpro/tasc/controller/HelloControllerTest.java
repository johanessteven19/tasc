package com.adpro.tasc.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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

@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void DefaultLoginMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));
    }

    @Test
    public void DefaultRegisterMessage() throws Exception {
        this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Register")));
    }

    @Test
    public void loginWithAdminTest() throws Exception {
        this.mockMvc
                .perform(get("/home-admin").with(user("admin").password("{noop}123").roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginWithStudentTest() throws Exception {
        this.mockMvc
                .perform(get("/home-student")
                        .with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginWithTATest() throws Exception {
        this.mockMvc
                .perform(get("/home-TA").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void loginWithNoRoleTest() throws Exception {
        this.mockMvc
                .perform(get("/home-TA").with(user("none").password("{noop}123").roles("UNASSIGNED")))
                .andDo(print()).andExpect(status().isForbidden());
    }

    @Test
    public void loginWithNoRole2() throws Exception {
        this.mockMvc
                .perform(get("/waiting").with(user("none").password("{noop}123").roles("UNASSIGNED")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void navAdminTest() throws Exception {
        this.mockMvc
                .perform(get("/nav-admin").with(user("admin").password("{noop}123").roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void navStudentTest() throws Exception {
        this.mockMvc
                .perform(get("/nav-student").with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void navTATest() throws Exception {
        this.mockMvc
                .perform(get("/nav-TA").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addRolesTest() throws Exception {
        this.mockMvc
                .perform(get("/add-roles").with(user("admin").password("{noop}123").roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createCoursesTest() throws Exception {
        this.mockMvc
                .perform(get("/create-course").with(user("admin").password("{noop}123").roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void reminderTest() throws Exception {
        this.mockMvc
                .perform(get("/reminder").with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void courseListTest() throws Exception {
        this.mockMvc
                .perform(get("/userlist-courses").with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void seeAppointmentAdminTest() throws Exception {
        this.mockMvc
                .perform(get("/see-appointment-admin").with(user("admin").password("{noop}123").roles("ADMIN")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void seeAppointmentTATest() throws Exception {
        this.mockMvc
                .perform(get("/see-appointment-TA").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void seeAppointmentStudentTest() throws Exception {
        this.mockMvc
                .perform(get("/see-appointment-student").with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void acceptRejectTest() throws Exception {
        this.mockMvc
                .perform(get("/accept_reject").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void createScheduleTest() throws Exception {
        this.mockMvc
                .perform(get("/create-schedule").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addSlotTest() throws Exception {
        this.mockMvc
                .perform(get("/add-slot").with(user("ta").password("{noop}123").roles("TEACHING_ASSISTANT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void bookAppointmentTest() throws Exception {
        this.mockMvc
                .perform(get("/book-appointment/home").with(user("student").password("{noop}123").roles("STUDENT")))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void bookAppointmentPickTATest() throws Exception {
        this.mockMvc
                .perform(get("/book-appointment/pick-ta").with(user("student").password("{noop}123").roles("STUDENT"))
                        .param("courseName","Software Engineering"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void bookAppointmentSeeTAScheduleTest() throws Exception {
        this.mockMvc
                .perform(get("/book-appointment/see-ta-schedule").with(user("student").password("{noop}123").roles("STUDENT"))
                        .param("taUserName","ta")
                        .param("courseName","Software Engineering"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void bookAppointmentBookTest() throws Exception {
        this.mockMvc
                .perform(get("/book-appointment/book").with(user("student").password("{noop}123").roles("STUDENT"))
                        .param("startTime","0")
                        .param("finishTime","3600000")
                        .param("day","MONDAY")
                        .param("taUserName","ta")
                        .param("courseName","Software Engineering")
                        .param("bookTime",String.valueOf(System.currentTimeMillis()))
                        .param("error","false"))
                .andDo(print()).andExpect(status().isOk());
    }





//    @Test
//    public void courseListJoinTest() throws Exception {
//        this.mockMvc
//                .perform(post("/userlist-courses/assign").with(user("student").password("{noop}123").roles("STUDENT"))
//                        .param("userName","student")
//                        .param("courseName","Software Engineering"))
//                .andDo(print()).andExpect(status().isOk());
//    }

}
