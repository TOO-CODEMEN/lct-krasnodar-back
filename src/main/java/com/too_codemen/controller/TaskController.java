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

    @PostMapping("/saveTask")
    public Task save(@RequestBody Task task) {
        return taskService.save(task);
    }

    @DeleteMapping("/deleteTask/{id}")
    public Task deleteTask(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

    @GetMapping("/showTaskInfo/{name}")
    public Task showTaskInfo(@PathVariable String name) {
        return taskService.showTaskInfo(name);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/getTaskByUserId/{id}")
    public List<Task> getTaskByUserId(@PathVariable Long id) {
        return taskService.getTaskByUserId(id);
    }
}
