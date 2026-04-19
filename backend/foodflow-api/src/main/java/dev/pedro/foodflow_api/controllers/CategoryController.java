package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.category.CategoryRequestDTO;
import dev.pedro.foodflow_api.dto.category.CategoryResponseDTO;
import dev.pedro.foodflow_api.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Categorias", description = "Endpoints para listagem e criação de categorias")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Retorna todas as categorias cadastradas no banco")
    public ResponseEntity<List<CategoryResponseDTO>> listCategories() {
        return ResponseEntity.ok(categoryService.listCategories());
    }

    @PostMapping
    @Operation(summary = "Cria uma nova categoria e salva no banco")
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequest) {
        var categoryCreated = categoryService.createCategory(categoryRequest);
        var uri = URI.create("/categorias/" + categoryCreated.id());
        return ResponseEntity.created(uri).body(categoryCreated);
    }
}
