package dev.pedro.foodflow_api.dto.restauranttable;

import java.util.UUID;

public record RestaurantTableResponseDTO(
        Long id,
        Integer number,
        boolean orderPending,
        UUID publicId
) {
}
