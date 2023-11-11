package com.too_codemen.controller;

import com.too_codemen.entity.*;
import com.too_codemen.model.CourseRequest;
import com.too_codemen.model.CuratorRequest;
import com.too_codemen.service.*;
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

    @Autowired
    private CuratorService curatorService;

    @PostMapping("/courses/saveCourse")
    public Course saveCourse(@RequestBody CourseRequest course) {
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

    @PostMapping("/curators/saveCurator")
    public Curator saveCurator(@RequestBody Curator curatorRequest) {
        return curatorService.saveCurator(curatorRequest);
    }

    @DeleteMapping("/curators/deleteCuratorById/{id}")
    public void deleteCuratorById(@PathVariable Long id) {
        curatorService.deleteCuratorById(id);
    }

    @GetMapping("/curators/getAllCurators")
    public List<Curator> getAllCurators() {
        return curatorService.getAllCurators();
    }

    @GetMapping("/curators/showCuratorInfo/{email}")
    public Curator showCuratorInfo(@PathVariable String email) {
        return curatorService.showCuratorInfo(email);
    }

    @PatchMapping("/curators/updateCuratorById/{id}")
    public Curator updateCuratorById(@PathVariable Long id, @RequestBody Curator curator) {
        return curatorService.updateCurator(id, curator);
    }

    @GetMapping("/curators/getUsersByCuratorId/{id}")
    public List<User> getUsersByCuratorId(@PathVariable Long id) {
        return curatorService.getUsersByCuratorId(id);
    }
}
