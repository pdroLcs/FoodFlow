package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.order.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RestaurantTableMapper.class)
public interface OrderMapper {

    OrderResponseDTO toDTO(Order order);

}
