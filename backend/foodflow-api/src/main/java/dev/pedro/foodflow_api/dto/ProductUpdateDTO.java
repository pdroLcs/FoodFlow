package dev.pedro.foodflow_api.dto;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        String name,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
