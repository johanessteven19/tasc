package com.adpro.tasc.user.db.model;

import com.adpro.tasc.appointment.db.model.Course;
import com.adpro.tasc.appointment.db.model.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AcademicUser extends User {
    public AcademicUser(String userName, String fullName, String password, Role role) {
        super(userName, fullName, password, role);
    }

    public AcademicUser(User user) {
        super(user.getUserName(), user.getFullName(), user.getPassword(), user.getRole());
    }

    private List<Course> courses;
    private Schedule schedule;
}
