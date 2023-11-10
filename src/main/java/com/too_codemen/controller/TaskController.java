package com.too_codemen.controller;

import com.too_codemen.entity.Task;
import com.too_codemen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/showTaskInfo/{name}")
    public Task showTaskInfo(@PathVariable String name) {
        return taskService.showTaskInfo(name);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/getTasksByUserId/{id}")
    public List<Task> getTaskByUserId(@PathVariable Long id) {
        return taskService.getTasksByUserId(id);
    }

    @PatchMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @GetMapping("/getTasksByCourseId/{id}")
    public List<Task> getTasksByCourseId(@PathVariable Long id) {
        return taskService.getTasksByCourseId(id);
    }
}
