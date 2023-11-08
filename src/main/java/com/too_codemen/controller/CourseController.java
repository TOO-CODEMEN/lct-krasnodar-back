package com.too_codemen.controller;

import com.too_codemen.entity.Course;
import com.too_codemen.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/getCourseById/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/getCourseByName/{name}")
    public Course getCourseByName(@PathVariable String name) {
        return courseService.getCourseByName(name);
    }

    @PostMapping("/saveCourse")
    public Course saveCourse(@RequestBody Course course) {
        System.out.println(course);
        System.out.println(course.getStartTime());
        System.out.println(course.getFinishTime());
        System.out.println(course.getDeadline());
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/deleteCourseById/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

    @GetMapping("/getAllCourses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getCoursesByUserId/{id}")
    public List<Course> getCoursesByUserId(@PathVariable Long id) {
        return courseService.getCoursesByUserId(id);
    }

    @PatchMapping("/updateCourseById/{id}")
    public Course updateCourseById(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }
}
