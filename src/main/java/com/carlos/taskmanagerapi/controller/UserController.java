package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.model.User;
import com.carlos.taskmanagerapi.service.UserService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController


@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cria um novo usuário no sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }
}
