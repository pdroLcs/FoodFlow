package dev.pedro.foodflow_api.dto.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(@NotNull String name) {
}
