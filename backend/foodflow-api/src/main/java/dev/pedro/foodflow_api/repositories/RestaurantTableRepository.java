package dev.pedro.foodflow_api.repositories;

import dev.pedro.foodflow_api.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
