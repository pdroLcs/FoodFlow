package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.OrderRequestDTO;
import dev.pedro.foodflow_api.dto.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.OrderStatus;
import dev.pedro.foodflow_api.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        var order = orderService.createOrder(orderRequest);
        var uri = URI.create("/pedidos/" + order.id());
        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> listOrders() {
        return ResponseEntity.ok(orderService.listOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
