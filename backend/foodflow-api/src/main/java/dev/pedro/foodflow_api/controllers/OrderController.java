package dev.pedro.foodflow_api.controllers;

import dev.pedro.foodflow_api.dto.order.OrderRequestDTO;
import dev.pedro.foodflow_api.dto.order.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.OrderStatus;
import dev.pedro.foodflow_api.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Pedidos", description = "Endpoints para gerenciamento dos pedidos")
@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(summary = "Cria um novo pedido e salva no banco")
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        var order = orderService.createOrder(orderRequest);
        var uri = URI.create("/pedidos/" + order.id());
        return ResponseEntity.created(uri).body(order);
    }

    @GetMapping
    @Operation(summary = "Lista todos os pedidos cadastrados no banco")
    public ResponseEntity<List<OrderResponseDTO>> listOrders() {
        return ResponseEntity.ok(orderService.listOrders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o pedido com base no Id fornecido")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Altera o estado do pedido e salva no banco")
    public ResponseEntity<OrderResponseDTO> updateStatus(@PathVariable Long id, @RequestBody OrderStatus status) {
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
