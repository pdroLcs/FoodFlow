package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.CategoryRequestDTO;
import dev.pedro.foodflow_api.dto.CategoryResponseDTO;
import dev.pedro.foodflow_api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequest) {
        var categoryCreated = categoryService.createCategory(categoryRequest);
        var uri = URI.create("/categorias/" + categoryCreated.id());
        return ResponseEntity.created(uri).body(categoryCreated);
    }
}
