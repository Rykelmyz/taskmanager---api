package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.dto.auth.LoginRequestDTO;
import com.carlos.taskmanagerapi.dto.auth.LoginResponseDTO;
import com.carlos.taskmanagerapi.model.User;
import com.carlos.taskmanagerapi.repository.UserRepository;
import com.carlos.taskmanagerapi.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Operation(
            summary = "Autenticar usuário",
            description = "Realiza login e retorna um token JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas")
    })

    @PostMapping("/login")
    public LoginResponseDTO login(
            @RequestBody LoginRequestDTO request
    ) {

        User user = userRepository.findByEmail(
                request.getEmail()
        ).orElseThrow();

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!passwordMatches) {
            throw new RuntimeException("Senha inválida");
        }

        String token =
                jwtService.generateToken(
                        user.getEmail(),
                        user.getRole().name()
                );

        return new LoginResponseDTO(token);
    }
}