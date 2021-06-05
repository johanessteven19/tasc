package com.adpro.tasc;

import com.adpro.tasc.controller.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestApp {

    @Autowired
    private HelloController controller;

    @Autowired
    private AddRolesController addRolesController;

    @Autowired
    private BookAppointmentController bookAppointmentController;

    @Autowired
    private CreateCoursesController createCoursesController;

    @Autowired
    private CreateScheduleController createScheduleController;

    @Autowired
    private SeeAppointmentController seeAppointmentController;

    @Autowired
    private UserListCoursesController userListCoursesController;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void AddRolesTest() {
        assertThat(addRolesController).isNotNull();
    }

    @Test
    void BookAppointmentTest() {
        assertThat(bookAppointmentController).isNotNull();
    }

    @Test
    void CreateCoursesControllerTest() {
        assertThat(createCoursesController).isNotNull();
    }

    @Test
    void CreateScheduleControllerTest() {
        assertThat(createScheduleController).isNotNull();
    }

    @Test
    void SeeAppointmentControllerTest() {
        assertThat(seeAppointmentController).isNotNull();
    }

    @Test
    void UserListCoursesControllerTest() {
        assertThat(userListCoursesController).isNotNull();
    }


}
