package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.product.ProductResponseDTO;
import dev.pedro.foodflow_api.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductResponseDTO toDTO(Product product);

}
