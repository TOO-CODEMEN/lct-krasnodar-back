package com.too_codemen.service;

import com.too_codemen.entity.Course;
import com.too_codemen.repository.CourseRepository;
import com.too_codemen.repository.MaterialRepository;
import com.too_codemen.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course getCourseByName(String name) {
        return courseRepository.findByName(name);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
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
        }
        if (updatedCourse.getAudience() != null) {
            existingCourse.setAudience(updatedCourse.getAudience());
        }
        return courseRepository.save(existingCourse);
    }
}
