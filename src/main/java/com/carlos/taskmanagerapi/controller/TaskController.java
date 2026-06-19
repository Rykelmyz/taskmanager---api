package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import com.carlos.taskmanagerapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Page<TaskResponseDTO> getAllTasks(Pageable pageable) {
        return taskService.findAllResponses(pageable);
    }

    @PostMapping("/tasks")
    public TaskResponseDTO createTask(
            @Valid @RequestBody TaskRequestDTO taskRequestDTO
    ) {
        return taskService.save(taskRequestDTO);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponseDTO getTaskById(
            @PathVariable Long id
    ) {
        return taskService.findResponseById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponseDTO updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO updatedTask
    ) {
        return taskService.update(id, updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteById(id);
    }
}