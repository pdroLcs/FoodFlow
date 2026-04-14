package dev.pedro.foodflow_api.dto.order;

import java.util.List;

public record OrderRequestDTO(Long tableId, List<OrderItemRequestDTO> items) {
}
