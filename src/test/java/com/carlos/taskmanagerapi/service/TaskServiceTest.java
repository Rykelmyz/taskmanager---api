package com.carlos.taskmanagerapi.service;

import com.carlos.taskmanagerapi.dto.TaskRequestDTO;
import com.carlos.taskmanagerapi.dto.TaskResponseDTO;
import com.carlos.taskmanagerapi.model.Priority;
import com.carlos.taskmanagerapi.model.Task;
import com.carlos.taskmanagerapi.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.carlos.taskmanagerapi.exception.TaskNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    @Test
    void shouldCreateTask() {
        TaskRepository repository = Mockito.mock(TaskRepository.class);
        TaskService taskService = new TaskService(repository);

        TaskRequestDTO dto = new TaskRequestDTO();
        dto.setTitle("Estudar testes");
        dto.setPriority(Priority.HIGH);

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Estudar testes");
        savedTask.setPriority(Priority.HIGH);
        savedTask.setCompleted(false);

        Mockito.when(repository.save(Mockito.any(Task.class)))
                .thenReturn(savedTask);

        TaskResponseDTO response = taskService.save(dto);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Estudar testes", response.getTitle());
        assertEquals(Priority.HIGH, response.getPriority());
        assertFalse(response.isCompleted());
    }

    @Test
    void shouldFindTaskById() {

        TaskRepository repository = Mockito.mock(TaskRepository.class);
        TaskService taskService = new TaskService(repository);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Estudar Spring");
        task.setPriority(Priority.HIGH);

        Mockito.when(repository.findById(1L))
                .thenReturn(java.util.Optional.of(task));

        Task result = taskService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Estudar Spring", result.getTitle());
    }

    @Test
    void shouldThrowTaskNotFoundException() {

        TaskRepository repository = Mockito.mock(TaskRepository.class);
        TaskService taskService = new TaskService(repository);

        Mockito.when(repository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.findById(999L)
        );
    }
}
