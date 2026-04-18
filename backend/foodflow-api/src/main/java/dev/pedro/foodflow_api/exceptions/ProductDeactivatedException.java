package dev.pedro.foodflow_api.exceptions;

public class ProductDeactivatedException extends RuntimeException {

    public ProductDeactivatedException(String message) {
        super(message);
    }

    public ProductDeactivatedException() {
        super("O produto está desativado");
    }
}
