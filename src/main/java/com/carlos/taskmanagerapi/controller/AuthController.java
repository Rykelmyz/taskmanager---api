package com.carlos.taskmanagerapi.controller;

import com.carlos.taskmanagerapi.dto.auth.LoginRequestDTO;
import com.carlos.taskmanagerapi.dto.auth.LoginResponseDTO;
import com.carlos.taskmanagerapi.model.User;
import com.carlos.taskmanagerapi.repository.UserRepository;
import com.carlos.taskmanagerapi.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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