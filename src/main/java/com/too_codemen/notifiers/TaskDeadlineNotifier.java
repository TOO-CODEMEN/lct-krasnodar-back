package com.too_codemen.notifiers;

import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import com.too_codemen.service.EmailService;
import com.too_codemen.service.TaskService;
import com.too_codemen.service.UserService;
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

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 50000)
    public void notifyUsersAboutTaskDeadline() {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            if (task.getStatus() == false) {
                if (isTaskDeadlineApproaching(task.getDeadline())) {
                    emailService.sendNotification(task.getUser().getEmail(), "Приближается дедлайн задачи",
                            "Дедлайн для задачи '" + task.getName() + "' приближается.");
                }
            }

        }
    }

    @Scheduled(fixedRate = 50000)
    public void finishUserTask() {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            if (task.getUser() != null) {
                if (task.getStatus() == false) {
                    if (isTaskDeadline(task.getDeadline())) {
                        emailService.sendNotification(task.getUser().getEmail(), "Задание завершено",
                                "Дедлайн для задания '" + task.getName() + "' прошел.");
                        User user1 = task.getUser();
                        User user = new User();
                        user.setId(user1.getId());
                        user.setFailedTasks(user1.getFailedTasks());
                        Long existingFailedTasks = user.getFailedTasks() + 1;
                        user.setFailedTasks(existingFailedTasks);
                        userService.updateUser(user.getId(), user);
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = 50000)
    public void finishCourseTask() {
        List<Task> tasks = taskService.getAllTasks();
        for (Task task : tasks) {
            if (task.getUser() == null) {
                if (task.getStatus() == false) {
                    if (isTaskDeadlineApproaching(task.getDeadline())) {
                        emailService.sendNotification(task.getCourse().getUser().getEmail(), "Задание завершено",
                                "Дедлайн для задания '" + task.getName() + "' прошел.");
                        User user1 = task.getCourse().getUser();
                        User user = new User();
                        user.setId(user1.getId());
                        user.setFailedTasks(user1.getFailedTasks());
                        Long existingFailedTasks = user.getFailedTasks() + 1;
                        user.setFailedTasks(existingFailedTasks);
                        userService.updateUser(user.getId(), user);
                    }
                }
            }
        }
    }

    private boolean isTaskDeadline(Timestamp deadline) {
        Instant currentTime = Instant.now();

        Instant deadlineInstant = deadline.toInstant();

        Duration duration = Duration.between(currentTime, deadlineInstant);

        if (duration.getSeconds() >= -100 && duration.getSeconds() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTaskDeadlineApproaching(Timestamp deadline) {
        Instant currentTime = Instant.now();

        Instant deadlineInstant = deadline.toInstant();

        Duration duration = Duration.between(currentTime, deadlineInstant);

        if (duration.getSeconds() <= 86400 && duration.getSeconds() > 86300) {
            return true;
        } else {
            return false;
        }
    }
}
