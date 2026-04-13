package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.product.ProductCreateDTO;
import dev.pedro.foodflow_api.dto.product.ProductUpdateDTO;
import dev.pedro.foodflow_api.dto.product.ProductResponseDTO;
import dev.pedro.foodflow_api.entities.Product;
import dev.pedro.foodflow_api.mappers.ProductMapper;
import dev.pedro.foodflow_api.repositories.CategoryRepository;
import dev.pedro.foodflow_api.repositories.ProductRepository;
import dev.pedro.foodflow_api.services.storage.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final StorageService storageService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper, StorageService storageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.storageService = storageService;
    }

    public ProductResponseDTO createProduct(ProductCreateDTO productRequest, MultipartFile image) {
        var category = categoryRepository.findById(productRequest.categoryId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        String imageUrl = storageService.upload(image);

        var product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .imageUrl(imageUrl)
                .category(category)
                .build();
        var productSaved = productRepository.save(product);
        return productMapper.toDTO(productSaved);
    }

    public List<ProductResponseDTO> listProducts(Long categoryId) {
        List<Product> products = categoryId == null ? productRepository.findAll() : productRepository.findByCategoryId(categoryId);
        return products.stream().map(productMapper::toDTO).toList();
    }

    public ProductResponseDTO getProduct(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if (!product.isActive()) throw new RuntimeException("O produto está desativado");
        return productMapper.toDTO(product);
    }

    public ProductResponseDTO updateProduct(Long id, ProductUpdateDTO productUpdate, MultipartFile image) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (productUpdate.name() != null) product.updateName(productUpdate.name());
        if (productUpdate.description() != null) product.updateDescription(productUpdate.description());
        if (productUpdate.price() != null) product.updatePrice(productUpdate.price());
        if (productUpdate.categoryId() != null) {
            var category = categoryRepository.findById(productUpdate.categoryId()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
            product.updateCategory(category);
        }
        if (image != null && !image.isEmpty()) {
            var imageUrl = storageService.upload(image);
            product.updateImageUrl(imageUrl);
        }
        return productMapper.toDTO(productRepository.save(product));
    }

    public ProductResponseDTO deactivateProduct(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.deactivate();
        var productSaved = productRepository.save(product);
        return productMapper.toDTO(productSaved);
    }

    public ProductResponseDTO activateProduct(Long id) {
        var product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        product.activate();
        var productSaved = productRepository.save(product);
        return productMapper.toDTO(productSaved);
    }
}
