package com.carlos.taskmanagerapi.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task com ID " + id + " não encontrada.");
    }
}