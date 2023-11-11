package com.too_codemen.notifiers;

import com.too_codemen.entity.Course;
import com.too_codemen.entity.User;
import com.too_codemen.service.CourseService;
import com.too_codemen.service.EmailService;
import com.too_codemen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.mail.SimpleMailMessage;
import java.sql.Timestamp;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
public class CourseDeadlineNotifier {

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 100000)
    public void notifyUsersAboutCourseDeadline() {
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            if (course.getStatus() == false) {
                if (isCourseDeadlineApproaching(course.getDeadline())) {
                    emailService.sendNotification(course.getUser().getEmail(), "Приближается дедлайн курса",
                            "Дедлайн для курса '" + course.getName() + "' приближается.");
                }
            }

        }
    }

    @Scheduled(fixedRate = 100000)
    public void finishCourse() {
        List<Course> courses = courseService.getAllCourses();
        for (Course course : courses) {
            if (course.getStatus() == false) {
                if (isCourseDeadline(course.getDeadline())) {
                    emailService.sendNotification(course.getUser().getEmail(), "Курс завершен",
                            "Дедлайн для курса '" + course.getName() + "' прошел.");
                    Instant currentTime = Instant.now();
                    course.setFinishTime(Timestamp.from(currentTime));
                    User user1 = course.getUser();
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

    private boolean isCourseDeadline(Timestamp deadline) {
        Instant currentTime = Instant.now();

        Instant deadlineInstant = deadline.toInstant();

        Duration duration = Duration.between(currentTime, deadlineInstant);

        if (duration.getSeconds() >= -100 && duration.getSeconds() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isCourseDeadlineApproaching(Timestamp deadline) {
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
