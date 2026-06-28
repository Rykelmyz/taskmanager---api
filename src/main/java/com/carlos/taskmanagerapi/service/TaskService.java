package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.exception.TaskNotFoundException;
import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.carlos.taskmanagerapi.model.Priority;
import org.springframework.data.jpa.domain.Specification;
import com.carlos.taskmanagerapi.model.User;
import com.carlos.taskmanagerapi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final UserRepository userRepository;

    public TaskService(
            TaskRepository repository,
            UserRepository userRepository
    ) {
        this.repository = repository;
        this.userRepository = userRepository;
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

        Task task = findByIdAndAuthenticatedUser(id);

        repository.delete(task);
    }

    public Task update(Long id, Task updatedTask) {

        Task task = findByIdAndAuthenticatedUser(id);

       
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
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        dto.setCreatedBy(task.getUser().getEmail());


        return dto;
    }

    public TaskResponseDTO findResponseById(Long id) {

        Task task = findByIdAndAuthenticatedUser(id);

        return toResponseDTO(task);
    }


    private Task toEntity(TaskRequestDTO dto) {

        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setPriority(dto.getPriority());

        return task;
    }

    public TaskResponseDTO save(TaskRequestDTO dto) {

        User user = getAuthenticatedUser();

        Task task = toEntity(dto);
        task.setUser(user);

        Task savedTask = repository.save(task);

        return toResponseDTO(savedTask);
    }

    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {

        Task task = findByIdAndAuthenticatedUser(id);

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

    public Page<TaskResponseDTO> findByCompleted(
            boolean completed,
            Pageable pageable
    ) {
        return repository.findByCompleted(completed, pageable)
                .map(this::toResponseDTO);
    }

    public Page<TaskResponseDTO> findByPriority(
            Priority priority,
            Pageable pageable
    ) {
        return repository.findByPriority(priority, pageable)
                .map(this::toResponseDTO);
    }

    public Page<TaskResponseDTO> findByTitle(
            String title,
            Pageable pageable
    ) {
        return repository
                .findByTitleContainingIgnoreCase(
                        title,
                        pageable
                )
                .map(this::toResponseDTO);
    }

    public Page<TaskResponseDTO> findWithFilters(
            Boolean completed,
            Priority priority,
            String title,
            Pageable pageable
    ) {
        Specification<Task> spec = Specification.where(null);

        User user = getAuthenticatedUser();

        spec = spec.and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("user"),
                        user
                )
        );

        if (completed != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(
                            root.get("completed"),
                            completed
                    )
            );
        }

        if (priority != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(
                            root.get("priority"),
                            priority
                    )
            );
        }

        if (title != null && !title.isBlank()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("title")),
                            "%" + title.toLowerCase() + "%"
                    )
            );
        }

        return repository.findAll(spec, pageable)
                .map(this::toResponseDTO);
    }

    private User getAuthenticatedUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    private Task findByIdAndAuthenticatedUser(Long id) {

        User user = getAuthenticatedUser();

        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (!task.getUser().getId().equals(user.getId())) {
            throw new TaskNotFoundException(id);
        }

        return task;
    }
}
