package dev.pedro.foodflow_api.repositories;

import dev.pedro.foodflow_api.entities.Order;
import dev.pedro.foodflow_api.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    boolean existsByTableIdAndStatus(Long tableId, OrderStatus status);

}
