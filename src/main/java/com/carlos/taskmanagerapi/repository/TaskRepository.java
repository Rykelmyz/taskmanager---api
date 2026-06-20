package com.carlos.taskmanagerapi.repository;

import com.carlos.taskmanagerapi.model.Priority;
import com.carlos.taskmanagerapi.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.carlos.taskmanagerapi.model.Priority;

public interface TaskRepository
        extends JpaRepository<Task, Long> {

    Page<Task> findByCompleted(
            boolean completed,
            Pageable pageable
    );

    Page <Task> findByPriority
            (Priority priority, Pageable pageable);

    Page<Task> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

}

