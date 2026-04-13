package dev.pedro.foodflow_api.dto.product;

import java.math.BigDecimal;

public record ProductUpdateDTO(
        String name,
        String description,
        BigDecimal price,
        Long categoryId
) {
}
