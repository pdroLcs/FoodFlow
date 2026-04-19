package dev.pedro.foodflow_api.dto.user;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDTO(
        @NotEmpty(message = "E-mail é obrigatório")
        String email,
        @NotEmpty(message = "Senha é obrigatório")
        String password) {
}
