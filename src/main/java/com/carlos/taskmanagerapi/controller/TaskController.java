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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(
            summary = "Listar tarefas",
            description = "Lista tarefas com suporte a paginação, ordenação e filtros por status, prioridade e título."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

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

        return taskService.findWithFilters(
                completed,
                priority,
                title,
                pageable
        );
    }

    @Operation(
            summary = "Criar tarefa",
            description = "Cria uma nova tarefa com título e prioridade."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @PostMapping("/tasks")
    public TaskResponseDTO createTask(
            @Valid @RequestBody TaskRequestDTO taskRequestDTO
    ) {
        return taskService.save(taskRequestDTO);
    }

    @Operation(
            summary = "Buscar tarefa por ID",
            description = "Retorna uma tarefa específica pelo seu ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @GetMapping("/tasks/{id}")
    public TaskResponseDTO getTaskById(
            @PathVariable Long id
    ) {
        return taskService.findResponseById(id);
    }

    @Operation(
            summary = "Atualizar tarefa",
            description = "Atualiza os dados de uma tarefa existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @PutMapping("/tasks/{id}")
    public TaskResponseDTO updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO updatedTask
    ) {
        return taskService.update(id, updatedTask);
    }

    @Operation(
            summary = "Excluir tarefa",
            description = "Remove uma tarefa pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa removida com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(
            @PathVariable Long id
    ) {
        taskService.deleteById(id);
    }
}