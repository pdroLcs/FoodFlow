package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.user.RegisterResponseDTO;
import dev.pedro.foodflow_api.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegisterUserMapper {

    RegisterResponseDTO toDTO(User user);

}
