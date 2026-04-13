package dev.pedro.foodflow_api.dto.order;

import java.util.List;

public record OrderRequestDTO(List<OrderItemRequestDTO> items) {
}
