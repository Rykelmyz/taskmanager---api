package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import com.carlos.taskmanagerapi.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.PageRequest;
import com.carlos.taskmanagerapi.model.Priority;
import org.springframework.data.domain.Sort;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public Page<TaskResponseDTO> getAllTasks(
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (completed != null) {
            return taskService.findByCompleted(completed, pageable);
        }

        if (priority != null) {
            return taskService.findByPriority(priority, pageable);
        }

        if (title != null && !title.isBlank()) {
            return taskService.findByTitle(title, pageable);
        }

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