package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.category.CategoryResponseDTO;
import dev.pedro.foodflow_api.entities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toDTO(Category category);

    Category toEntity(String name);

}
