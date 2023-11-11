package com.too_codemen.notifiers;

import com.too_codemen.entity.Task;
import com.too_codemen.service.EmailService;
import com.too_codemen.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class TaskDeadlineNotifier {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 43200)
    public void notifyUsersAboutTaskDeadline() {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            if (task.getStatus() == false) {
                if (isTaskDeadline(task.getDeadline())) {
                    emailService.sendNotification(task.getUser().getEmail(), "Приближается дедлайн задачи",
                            "Дедлайн для задачи '" + task.getName() + "' приближается.");
                }
            }

        }
    }

    @Scheduled(fixedRate = 43200)
    public void finishTask() {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            if (task.getStatus() == false) {
                if (isTaskDeadlineApproaching(task.getDeadline())) {
                    emailService.sendNotification(task.getUser().getEmail(), "Задание завершено",
                            "Дедлайн для задания '" + task.getName() + "' прошел.");
                    task.setStatus(true);
                    taskService.updateTask(task.getId(), task);
                }
            }

        }
    }

    private boolean isTaskDeadline(Timestamp deadline) {
        Instant currentTime = Instant.now();

        Instant deadlineInstant = deadline.toInstant();

        Duration duration = Duration.between(currentTime, deadlineInstant);

        if (duration.getSeconds() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTaskDeadlineApproaching(Timestamp deadline) {
        Instant currentTime = Instant.now();

        Instant deadlineInstant = deadline.toInstant();

        Duration duration = Duration.between(currentTime, deadlineInstant);

        if (duration.getSeconds() <= 86400) {
            return true;
        } else {
            return false;
        }
    }
}
