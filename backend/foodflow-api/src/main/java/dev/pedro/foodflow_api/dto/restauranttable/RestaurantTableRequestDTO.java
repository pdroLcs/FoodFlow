package dev.pedro.foodflow_api.dto.restauranttable;

import jakarta.validation.constraints.NotNull;

public record RestaurantTableRequestDTO(@NotNull Integer number) {
}
