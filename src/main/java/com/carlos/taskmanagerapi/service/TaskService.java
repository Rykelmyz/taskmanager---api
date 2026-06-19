package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.exception.TaskNotFoundException;
import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    private TaskResponseDTO toResponseDTO(Task task) {

        TaskResponseDTO dto = new TaskResponseDTO();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setPriority(task.getPriority());
        dto.setCompleted(task.isCompleted());

        return dto;
    }

    public TaskResponseDTO findResponseById(Long id) {

        Task task = findById(id);

        return toResponseDTO(task);
    }

    private Task toEntity(TaskRequestDTO dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setPriority(dto.getPriority());

        return task;
    }

    public TaskResponseDTO save(TaskRequestDTO dto) {

        Task task = toEntity(dto);

        Task savedTask = repository.save(task);

        return toResponseDTO(savedTask);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {

        Task task = findById(id);

        task.setTitle(dto.getTitle());
        task.setPriority(dto.getPriority());

        Task updatedTask = repository.save(task);

        return toResponseDTO(updatedTask);
    }

    public List<TaskResponseDTO> findAllResponses() {

        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Page<TaskResponseDTO> findAllResponses(Pageable pageable) {

        return repository.findAll(pageable)
                .map(this::toResponseDTO);
    }
}
