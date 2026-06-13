package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.exception.TaskNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Task update(Long id, Task updatedTask) {

        Task task = findById(id);

       
        task.setTitle(updatedTask.getTitle());
        task.setPriority(updatedTask.getPriority());
        task.setCompleted(updatedTask.isCompleted());

        return repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
