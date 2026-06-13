package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.repository.TaskRepository;
import com.carlos.taskmanagerapi.service.TaskService;
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

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {

        return taskService.findAll();
    }

    @PostMapping("/tasks")
    public Task createTask(
            @RequestBody Task task
    ) {

        return taskService.save(task);
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(
            @PathVariable Long id
    ) {

        return taskService.findById(id);

    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @RequestBody Task updatedTask
    ) {

        Task task = taskService.findById(id);


        if (task == null) {
            return null;
        }

        task.setTitle(updatedTask.getTitle());
        task.setPriority(updatedTask.getPriority());
        task.setCompleted(updatedTask.isCompleted());

        return taskService.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id
    ) {

        taskService.deleteById(id);
    }
}