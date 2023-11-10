package com.too_codemen.notifiers;

import com.too_codemen.entity.Course;
import com.too_codemen.service.CourseService;
import com.too_codemen.service.EmailService;
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

    @Scheduled(fixedRate = 43200)
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

    private boolean isCourseDeadlineApproaching(Timestamp deadline) {
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
