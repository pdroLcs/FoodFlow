package dev.pedro.foodflow_api.exceptions;

public class CategoryHasProductsException extends RuntimeException {
    public CategoryHasProductsException() {
        super("Categoria possui produtos");
    }
}
