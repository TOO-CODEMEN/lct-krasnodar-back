package com.too_codemen.controller;

import com.too_codemen.entity.Course;
import com.too_codemen.entity.Material;
import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import com.too_codemen.model.CourseRequest;
import com.too_codemen.service.CourseService;
import com.too_codemen.service.MaterialService;
import com.too_codemen.service.TaskService;
import com.too_codemen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/courses/saveCourse")
    public Course saveCourse(@RequestBody CourseRequest course) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);
        return courseService.saveCourse(course);
    }
    @DeleteMapping("/courses/deleteCourseById/{id}")
    public void deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

    @PostMapping("/materials/saveMaterial")
    public Material saveMaterial(@RequestBody Material material) {
        return materialService.save(material);
    }

    @DeleteMapping("/materials/deleteMaterialById/{id}")
    public void deleteMaterialById(@PathVariable Long id) {
        materialService.deleteMaterialById(id);
    }

    @PatchMapping("/materials/updateMaterial/{id}")
    public Material updateMaterial(@PathVariable Long id, @RequestBody Material material) {
        return materialService.updateMaterial(id, material);
    }

    @PostMapping("/tasks/saveTask")
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/tasks/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

    @PostMapping("/users/saveUser")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/users/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/users/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/users/updateUser/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }


}
