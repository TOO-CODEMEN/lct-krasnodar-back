package com.too_codemen.repository;

import com.too_codemen.entity.Material;
import com.too_codemen.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);

    Task findByName(String name);

    void deleteTaskById(Long id);

    List<Task> findAll();

    List<Task> findTaskByUserId(Long id);

    void deleteTasksByUserId(Long id);

    void deleteTaskByCourseId(Long id);

    List<Task> findTaskByCourseId(Long id);

}
