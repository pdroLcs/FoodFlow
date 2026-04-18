package dev.pedro.foodflow_api.services;

import dev.pedro.foodflow_api.dto.order.OrderRequestDTO;
import dev.pedro.foodflow_api.dto.order.OrderResponseDTO;
import dev.pedro.foodflow_api.entities.Order;
import dev.pedro.foodflow_api.entities.OrderItem;
import dev.pedro.foodflow_api.entities.OrderStatus;
import dev.pedro.foodflow_api.exceptions.OrderNotFoundException;
import dev.pedro.foodflow_api.exceptions.ProductNotFoundException;
import dev.pedro.foodflow_api.exceptions.RestaurantTableNotFoundException;
import dev.pedro.foodflow_api.mappers.OrderMapper;
import dev.pedro.foodflow_api.repositories.OrderRepository;
import dev.pedro.foodflow_api.repositories.ProductRepository;
import dev.pedro.foodflow_api.repositories.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, RestaurantTableRepository restaurantTableRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.restaurantTableRepository = restaurantTableRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        var table = restaurantTableRepository.findById(orderRequest.tableId()).orElseThrow(RestaurantTableNotFoundException::new);

        if (table.isFree()) table.setFree(false);

        var order = Order.builder()
                .table(table)
                .build();

        orderRequest.items().forEach(orderItem -> {
            var product = productRepository.findById(orderItem.productId()).orElseThrow(ProductNotFoundException::new);
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
        return orderMapper.toDTO(orderRepository.findById(id).orElseThrow(OrderNotFoundException::new));
    }

    public OrderResponseDTO updateStatus(Long id, OrderStatus newStatus) {
        var order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        order.updateStatus(newStatus);
        return orderMapper.toDTO(orderRepository.save(order));
    }
}
