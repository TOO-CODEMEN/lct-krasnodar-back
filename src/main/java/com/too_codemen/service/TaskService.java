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

    public List<Task> getTasksByCourseId(Long id) {
        return taskRepository.findTaskByCourseId(id);
    }

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

    public List<Task> getTasksByUserId(Long id) {
        return taskRepository.findTaskByUserId(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (updatedTask.getName() != null) {
            existingTask.setName(updatedTask.getName());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getStatus() != null) {
            existingTask.setStatus(updatedTask.getStatus());
        }
        if (updatedTask.getDeadline() != null) {
            existingTask.setDeadline(updatedTask.getDeadline());
        }
        if (updatedTask.getCourse() != null) {
            existingTask.setCourse(updatedTask.getCourse());
        }
        return taskRepository.save(existingTask);
    }

}
