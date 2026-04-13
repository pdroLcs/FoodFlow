package dev.pedro.foodflow_api.dto;

import dev.pedro.foodflow_api.entities.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        OrderStatus status,
        BigDecimal totalValue,
        LocalDateTime creationDate,
        List<OrderItemResponseDTO> items
) {
}
