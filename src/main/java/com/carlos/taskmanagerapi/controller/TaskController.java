package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.repository.TaskRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {

        return repository.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(
            @RequestBody Task task
    ) {

        return repository.save(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(
            @PathVariable Long id
    ) {

        return repository.findById(id)
                .orElse(null);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask
    ) {

        Task task = repository.findById(id)
                .orElse(null);

        if (task == null) {
            return null;
        }

        task.setTitle(updatedTask.getTitle());
        task.setPriority(updatedTask.getPriority());
        task.setCompleted(updatedTask.isCompleted());

        return repository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id
    ) {

        repository.deleteById(id);
    }
}