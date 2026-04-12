package dev.pedro.foodflow_api.dto;

import dev.pedro.foodflow_api.entities.Category;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
