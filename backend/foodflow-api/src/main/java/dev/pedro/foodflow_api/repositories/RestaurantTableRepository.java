package dev.pedro.foodflow_api.repositories;

import dev.pedro.foodflow_api.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {

    Optional<RestaurantTable> findByNumber(Integer number);

    Optional<RestaurantTable> findByPublicId(UUID publicId);

}
