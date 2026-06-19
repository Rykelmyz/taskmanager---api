package com.carlos.taskmanagerapi.dto;

import com.carlos.taskmanagerapi.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TaskRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotNull(message = "A prioridade é obrigatória")
    private Priority priority;

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}
