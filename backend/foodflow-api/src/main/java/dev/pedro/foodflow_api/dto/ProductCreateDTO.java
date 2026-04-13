package dev.pedro.foodflow_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateDTO(
        @NotNull String name,
        @NotNull String description,
        @NotNull @Positive BigDecimal price,
        @NotNull Long categoryId
) {
}
