package dev.pedro.foodflow_api.dto.order;

import dev.pedro.foodflow_api.dto.product.ProductSummaryDTO;

import java.math.BigDecimal;

public record OrderItemResponseDTO(
        Long id,
        Integer quantity,
        BigDecimal unitPrice,
        ProductSummaryDTO product
) {
}
