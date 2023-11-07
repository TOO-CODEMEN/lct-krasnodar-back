package com.too_codemen.service;

import com.too_codemen.entity.Task;
import com.too_codemen.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task deleteTaskById(Long id) {
        return taskRepository.deleteTaskById(id);
    }

    public Task showTaskInfo(String name) {
        return taskRepository.findByName(name);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTaskByUserId(Long id) {
        return taskRepository.findTaskByUserId(id);
    }
}
