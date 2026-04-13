package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.ProductCreateDTO;
import dev.pedro.foodflow_api.dto.ProductUpdateDTO;
import dev.pedro.foodflow_api.dto.ProductResponseDTO;
import dev.pedro.foodflow_api.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listProducts(@RequestParam(required = false) Long categoryId) {
        return ResponseEntity.ok(productService.listProducts(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable Long id) {
        return  ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Long categoryId,
            @RequestParam MultipartFile image
    ) {
        var productRequest = new ProductCreateDTO(name, description, price, categoryId);
        var product = productService.createProduct(productRequest, image);
        var uri = URI.create("/produtos/" + product.id());
        return ResponseEntity.created(uri).body(product);
    }

    @PatchMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) MultipartFile image
    ) {
        var productUpdate = new ProductUpdateDTO(name, description, price, categoryId);
        var updated = productService.updateProduct(id, productUpdate, image);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<ProductResponseDTO> deactivateProduct(@PathVariable Long id) {
        var product = productService.deactivateProduct(id);
        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<ProductResponseDTO> activateProduct(@PathVariable Long id) {
        var product = productService.activateProduct(id);
        return ResponseEntity.ok(product);
    }
}
