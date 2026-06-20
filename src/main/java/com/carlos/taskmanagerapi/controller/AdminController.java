package com.carlos.taskmanagerapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class AdminController {

    @PreAuthorize("hasRole('ADMIN')")

    @Operation(
            summary = "Área administrativa",
            description = "Endpoint acessível apenas para usuários ADMIN."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Acesso autorizado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
    })

    @GetMapping("/admin")
    public String admin() {
        return "Área administrativa";
    }
}