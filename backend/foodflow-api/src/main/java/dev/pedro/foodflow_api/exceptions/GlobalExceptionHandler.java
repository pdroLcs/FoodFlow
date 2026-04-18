package dev.pedro.foodflow_api.exceptions;

import dev.pedro.foodflow_api.dto.error.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, OrderNotFoundException.class, CategoryNotFoundException.class, RestaurantTableNotFoundException.class})
    public ResponseEntity<ErrorResponseDTO> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(ProductDeactivatedException.class)
    public ResponseEntity<ErrorResponseDTO> handleDeactivated(ProductDeactivatedException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDTO("BAD_REQUEST", ex.getMessage()));
    }

}
