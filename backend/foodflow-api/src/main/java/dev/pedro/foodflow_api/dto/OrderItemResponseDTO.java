package dev.pedro.foodflow_api.dto;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        Integer quantity,
        BigDecimal unitPrice,
        ProductSummaryDTO product
) {
}
