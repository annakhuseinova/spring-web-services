package com.annakhuseinova.springwebservices.service;

import com.annakhuseinova.springwebservices.model.Course;
import com.annakhuseinova.springwebservices.model.ResponseStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseDetailsService {

    private static List<Course> courses = new ArrayList<>();

    static {
        Course springCourse = new Course(1, "Spring", "10 steps");
        courses.add(springCourse);
        Course springMvcCourse = new Course(2, "Spring MVC", "10 examples");
        courses.add(springMvcCourse);
        Course springBootCourse = new Course(3, "Spring Boot", "6K Students");
        courses.add(springBootCourse);
        Course mavenCourse = new Course(4, "Maven", "Most popular course on Maven");
        courses.add(mavenCourse);
    }

    public Optional<Course> findById(int id){
        return courses.stream().findFirst().filter(course -> course.getId() == id);
    }

    public List<Course> findAll(){
        return courses;
    }

    public ResponseStatus deleteById(int id){
        Optional<Course> courseToDelete = courses.stream().findAny().filter(course -> course.getId() == id);
        if (courseToDelete.isPresent()){
            courses.remove(courseToDelete.get());
            return ResponseStatus.SUCCESS;
        }
        return ResponseStatus.FAILURE;
    }
}
