package com.too_codemen.service;

import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import com.too_codemen.repository.TaskRepository;
import com.too_codemen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<Task> getTasksByCourseId(Long id) {
        return taskRepository.findTaskByCourseId(id);
    }

    public Task save(Task task) {
        User user = userRepository.findById(task.getUser().getId()).orElse(null);
        emailService.sendNotification(user.getEmail(), "Вам добавили новую задачу",
                "Вам добавили новую задачу '" + task.getName() + "' Успейте пройти до " + task.getDeadline());
        return taskRepository.save(task);
    }

    @Transactional
    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);
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
