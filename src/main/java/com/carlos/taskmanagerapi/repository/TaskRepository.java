package com.carlos.taskmanagerapi.repository;

import com.carlos.taskmanagerapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository
        extends JpaRepository<Task, Long> {
}