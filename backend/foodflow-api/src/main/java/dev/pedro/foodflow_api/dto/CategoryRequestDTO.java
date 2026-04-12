package dev.pedro.foodflow_api.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(@NotNull String name) {
}
