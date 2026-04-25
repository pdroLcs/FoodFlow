package dev.pedro.foodflow_api.mappers;

import dev.pedro.foodflow_api.dto.product.ProductDetailsDTO;
import dev.pedro.foodflow_api.dto.product.ProductResponseDTO;
import dev.pedro.foodflow_api.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductResponseDTO toDTO(Product product);

    @Mapping(target = "active", source = "active")
    ProductDetailsDTO toDetails(Product product);

}
