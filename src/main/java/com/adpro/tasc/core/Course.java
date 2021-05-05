package com.adpro.tasc.core;

public class Course {

    private Course courseName;

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
        CourseList.addCourse(courseName);
    }

    public Course getCourseName() {
        return courseName;
    }



}
