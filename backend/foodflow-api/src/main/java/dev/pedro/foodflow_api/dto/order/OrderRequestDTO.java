package dev.pedro.foodflow_api.dto.order;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(UUID publicId, List<OrderItemRequestDTO> items) {
}
