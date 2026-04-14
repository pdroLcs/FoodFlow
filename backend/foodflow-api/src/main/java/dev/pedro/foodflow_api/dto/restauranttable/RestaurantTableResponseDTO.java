package dev.pedro.foodflow_api.dto.restauranttable;

public record RestaurantTableResponseDTO(
        Long id,
        Integer number,
        boolean free
) {
}
