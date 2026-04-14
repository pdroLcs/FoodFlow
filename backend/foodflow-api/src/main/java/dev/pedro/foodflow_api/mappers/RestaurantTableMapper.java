package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableRequestDTO;
import dev.pedro.foodflow_api.dto.restauranttable.RestaurantTableResponseDTO;
import dev.pedro.foodflow_api.entities.RestaurantTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantTableMapper {

    RestaurantTableResponseDTO toDTO(RestaurantTable restaurantTable);

    RestaurantTable toEntity(RestaurantTableRequestDTO request);

}
