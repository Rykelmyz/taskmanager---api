package com.carlos.taskmanagerapi.repository;

import com.carlos.taskmanagerapi.model.Priority;
import com.carlos.taskmanagerapi.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskRepository
        extends JpaRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {

    Page<Task> findByCompleted(
            boolean completed,
            Pageable pageable
    );

    Page<Task> findByPriority(
            Priority priority,
            Pageable pageable
    );

    Page<Task> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

    Page<Task> findByUserEmail(
            String email,
            Pageable pageable
    );

    Page<Task> findByCompletedAndUserEmail(
            boolean completed,
            String email,
            Pageable pageable
    );

    Page<Task> findByPriorityAndUserEmail(
            Priority priority,
            String email,
            Pageable pageable
    );

    Page<Task> findByTitleContainingIgnoreCaseAndUserEmail(
            String title,
            String email,
            Pageable pageable
    );
}

