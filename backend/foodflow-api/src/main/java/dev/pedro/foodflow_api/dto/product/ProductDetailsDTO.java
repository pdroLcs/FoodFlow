package dev.pedro.foodflow_api.dto.product;

import dev.pedro.foodflow_api.dto.category.CategoryResponseDTO;

import java.math.BigDecimal;

public record ProductDetailsDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl,
        CategoryResponseDTO category,
        boolean active
) {
}
