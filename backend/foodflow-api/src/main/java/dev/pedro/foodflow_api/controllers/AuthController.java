package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.user.LoginRequestDTO;
import dev.pedro.foodflow_api.dto.user.LoginResponseDTO;
import dev.pedro.foodflow_api.dto.user.RegisterRequestDTO;
import dev.pedro.foodflow_api.dto.user.RegisterResponseDTO;
import dev.pedro.foodflow_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "auth", description = "Endpoints responsáveis pela autenticação dos admins")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Operation(summary = "Realiza o login do admin")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    @Operation(summary = "Realiza o registro do admin")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(request));
    }
}
