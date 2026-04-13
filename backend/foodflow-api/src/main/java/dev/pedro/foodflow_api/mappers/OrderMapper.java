package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    public OrderResponseDTO toDTO(Order order);

}
