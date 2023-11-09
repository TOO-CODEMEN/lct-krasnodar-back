package com.too_codemen.repository;

import com.too_codemen.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.materials LEFT JOIN FETCH c.tasks")
    List<Course> findAllWithMaterialsAndTasks();

}
