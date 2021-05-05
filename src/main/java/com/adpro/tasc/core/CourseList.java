package com.adpro.tasc.core;

import java.util.ArrayList;
import java.util.List;

public class CourseList {

    private static List<Course> courseList = new ArrayList<>();

    public static void addCourse(Course courseName) {
        courseList.add(courseName);
    }


}
