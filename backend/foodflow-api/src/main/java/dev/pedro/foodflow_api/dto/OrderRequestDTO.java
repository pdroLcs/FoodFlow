package dev.pedro.foodflow_api.dto;

import java.util.List;

public record OrderRequestDTO(List<OrderItemRequestDTO> items) {
}
