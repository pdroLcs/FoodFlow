package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.OrderRequestDTO;
import dev.pedro.foodflow_api.dto.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.Order;
import dev.pedro.foodflow_api.entities.OrderItem;
import dev.pedro.foodflow_api.entities.OrderStatus;
import dev.pedro.foodflow_api.mappers.OrderMapper;
import dev.pedro.foodflow_api.repositories.OrderItemRepository;
import dev.pedro.foodflow_api.repositories.OrderRepository;
import dev.pedro.foodflow_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        var order = Order.builder().build();

        orderRequest.items().forEach(orderItem -> {
            var product = productRepository.findById(orderItem.productId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            var item = OrderItem.builder()
                    .product(product)
                    .quantity(orderItem.quantity())
                    .unitPrice(product.getPrice())
                    .build();
            order.addItem(item);
        });

        order.calculateTotal();
        return orderMapper.toDTO(orderRepository.save(order));
    }

    public List<OrderResponseDTO> listOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toDTO).toList();
    }

    public OrderResponseDTO getOrder(Long id) {
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado")));
    }

    public OrderResponseDTO updateStatus(Long id, OrderStatus newStatus) {
        var order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        order.updateStatus(newStatus);
        return orderMapper.toDTO(orderRepository.save(order));
    }
}
