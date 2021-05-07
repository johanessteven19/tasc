package com.adpro.tasc.core;

public class Admin extends User{

    public Admin(String userName, String fullName, String role) {
        super(userName, fullName, role);
    }

    public void setRole(User user, String role) {
        user.role = role;
    }

    public void createCourses(Course course) {
        CourseList.addCourse(course);
    }

   /* public void setCourseTA(Course course, TeachingAssistant TA) {

    }*/

    public AppointmentRequest seeAppointment(AppointmentRequest appointmentRequest) {
        if (appointmentRequest.getAdminHasPermission() == true)
            return appointmentRequest;
        else return null;
   }



}
