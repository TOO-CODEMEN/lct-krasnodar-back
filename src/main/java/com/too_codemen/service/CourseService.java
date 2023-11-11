package com.too_codemen.service;

import com.too_codemen.entity.Course;
import com.too_codemen.entity.Material;
import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import com.too_codemen.model.CourseRequest;
import com.too_codemen.repository.CourseRepository;
import com.too_codemen.repository.MaterialRepository;
import com.too_codemen.repository.TaskRepository;
import com.too_codemen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Course saveCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        course.setMaterials(courseRequest.getMaterials());
        course.setTasks(courseRequest.getTasks());
        course.setUser(courseRequest.getUser());
        course.setStartTime(courseRequest.getStartTime());
        course.setFinishTime(courseRequest.getFinishTime());
        course.setDeadline(courseRequest.getDeadline());
        course.setStatus(courseRequest.getStatus());
        course.setAudience(courseRequest.getAudience());
        courseRepository.save(course);
        User user = userRepository.findById(courseRequest.getUser().getId()).orElse(null);
        emailService.sendNotification(user.getEmail(), "Вам добавили новый курс",
                "Вам добавили новый курс '" + course.getName() + "' Успейте пройти до " + course.getDeadline());
        // Обновление материалов
        for (Material material : course.getMaterials()) {
            updateMaterialCourseId(material.getId(), course.getId());
        }

        // Обновление задач
        for (Task task : course.getTasks()) {
            updateTaskCourseId(task.getId(), course.getId());
        }

        System.out.println("Returning course");
        return course;
    }


    private void updateMaterialCourseId(Long materialId, Long courseId) {
        String updateQuery = "UPDATE Material SET course_id = :courseId WHERE material_id = :materialId";
        entityManager.createQuery(updateQuery)
                .setParameter("courseId", courseId)
                .setParameter("materialId", materialId)
                .executeUpdate();
    }

    private void updateTaskCourseId(Long taskId, Long courseId) {
        String updateQuery = "UPDATE Task SET course_id = :courseId WHERE task_id = :taskId";
        entityManager.createQuery(updateQuery)
                .setParameter("courseId", courseId)
                .setParameter("taskId", taskId)
                .executeUpdate();
    }

    @Transactional
    public void deleteCourseById(Long id) {
        taskRepository.deleteTaskByCourseId(id);
        materialRepository.deleteMaterialByCourseId(id);
        courseRepository.deleteCourseById(id);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByUserId(Long id) {
        return courseRepository.findCourseByUserId(id);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).orElse(null);
        if (updatedCourse.getName() != null) {
            existingCourse.setName(updatedCourse.getName());
        }
        if (updatedCourse.getMaterials() != null) {
            existingCourse.setMaterials(updatedCourse.getMaterials());
        }
        if (updatedCourse.getTasks() != null) {
            existingCourse.setTasks(updatedCourse.getTasks());
        }
        if (updatedCourse.getUser() != null) {
            existingCourse.setUser(updatedCourse.getUser());
        }
        if (updatedCourse.getStartTime() != null) {
            existingCourse.setStartTime(updatedCourse.getStartTime());
        }
        if (updatedCourse.getFinishTime() != null) {
            existingCourse.setFinishTime(updatedCourse.getFinishTime());
        }
        if (updatedCourse.getDeadline() != null) {
            existingCourse.setDeadline(updatedCourse.getDeadline());
        }
        if (updatedCourse.getStatus() != null) {
            existingCourse.setStatus(updatedCourse.getStatus());
            if (existingCourse.getStatus() == true) {
                emailService.sendNotification(existingCourse.getUser().getCurator().getEmail(),
                        "Пользователь завершил курс", "Пользователь " + existingCourse.getUser().getName() +
                                " завершил курс " + existingCourse.getName());
            }
        }
        if (updatedCourse.getAudience() != null) {
            existingCourse.setAudience(updatedCourse.getAudience());
        }
        return courseRepository.save(existingCourse);
    }
}
