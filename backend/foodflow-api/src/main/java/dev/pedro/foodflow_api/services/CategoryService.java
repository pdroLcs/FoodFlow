package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.category.CategoryRequestDTO;
import dev.pedro.foodflow_api.dto.category.CategoryResponseDTO;
import dev.pedro.foodflow_api.entities.Category;
import dev.pedro.foodflow_api.exceptions.CategoryHasProductsException;
import dev.pedro.foodflow_api.exceptions.CategoryNotFoundException;
import dev.pedro.foodflow_api.mappers.CategoryMapper;
import dev.pedro.foodflow_api.repositories.CategoryRepository;
import dev.pedro.foodflow_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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

    public void deleteCategory(Long id) {
        if (!productRepository.findByCategoryId(id).isEmpty())
            throw new CategoryHasProductsException();
        categoryRepository.delete(categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new));
    }
}
