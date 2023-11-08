package com.too_codemen.repository;

import com.too_codemen.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findById(Long id);

    Course findByName(String name);

    Course save(Course course);

    void deleteCourseById(Long id);

    List<Course> findAll();

    List<Course> findCourseByUserId(Long id);
}
