package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import com.carlos.taskmanagerapi.exception.TaskNotFoundException;
import com.carlos.taskmanagerapi.model.Priority;
import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.repository.TaskRepository;
import com.carlos.taskmanagerapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {




    @Test
    void shouldCreateTask() {
        TaskRepository repository = Mockito.mock(TaskRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        TaskService taskService = new TaskService(
                repository,
                userRepository
        );

        org.springframework.security.core.Authentication authentication =
                Mockito.mock(org.springframework.security.core.Authentication.class);

        org.springframework.security.core.context.SecurityContext securityContext =
                Mockito.mock(org.springframework.security.core.context.SecurityContext.class);

        Mockito.when(securityContext.getAuthentication())
                .thenReturn(authentication);

        Mockito.when(authentication.getName())
                .thenReturn("teste@email.com");

        org.springframework.security.core.context.SecurityContextHolder
                .setContext(securityContext);

        com.carlos.taskmanagerapi.model.User user =
                new com.carlos.taskmanagerapi.model.User();

        user.setId(1L);
        user.setEmail("teste@email.com");

        Mockito.when(userRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(user));

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Estudar testes");
        dto.setPriority(Priority.HIGH);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Estudar testes");
        savedTask.setPriority(Priority.HIGH);
        savedTask.setCompleted(false);
        savedTask.setUser(user);

        Mockito.when(repository.save(Mockito.any(Task.class)))
                .thenReturn(savedTask);

        TaskResponseDTO response = taskService.save(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Estudar testes", response.getTitle());
        assertEquals(Priority.HIGH, response.getPriority());
        assertFalse(response.isCompleted());
        assertEquals("teste@email.com", response.getCreatedBy());

        org.springframework.security.core.context.SecurityContextHolder.clearContext();
    }

    @Test
    void shouldFindTaskById() {
        TaskRepository repository = Mockito.mock(TaskRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        TaskService taskService = new TaskService(
                repository,
                userRepository
        );

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Estudar Spring");
        task.setPriority(Priority.HIGH);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(task));

        Task result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Estudar Spring", result.getTitle());
    }

    @Test
    void shouldThrowTaskNotFoundException() {
        TaskRepository repository = Mockito.mock(TaskRepository.class);
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        TaskService taskService = new TaskService(
                repository,
                userRepository
        );

        Mockito.when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.findById(999L)
        );
    }
}
