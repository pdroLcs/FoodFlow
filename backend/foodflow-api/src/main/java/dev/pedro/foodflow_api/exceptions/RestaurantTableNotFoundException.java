package dev.pedro.foodflow_api.exceptions;

public class RestaurantTableNotFoundException extends RuntimeException {
    public RestaurantTableNotFoundException(String message) {
        super(message);
    }

    public RestaurantTableNotFoundException() {
        super("Mesa não encontrada");
    }
}
