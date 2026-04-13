package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.category.CategoryRequestDTO;
import dev.pedro.foodflow_api.dto.category.CategoryResponseDTO;
import dev.pedro.foodflow_api.entities.Category;
import dev.pedro.foodflow_api.mappers.CategoryMapper;
import dev.pedro.foodflow_api.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest) {
        var category = Category.builder().name(categoryRequest.name()).build();
        var savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    public List<CategoryResponseDTO> listCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDTO).toList();
    }
}
