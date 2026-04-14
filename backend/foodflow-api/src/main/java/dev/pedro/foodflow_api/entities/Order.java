package dev.pedro.foodflow_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static dev.pedro.foodflow_api.entities.OrderStatus.PENDING;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Builder.Default
    @Column(nullable = false)
    private BigDecimal totalValue = BigDecimal.ZERO;

    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;

    @PrePersist
    public void prePersist() {
        this.creationDate = LocalDateTime.now();
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
    }

    public void calculateTotal() {
        totalValue = items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void updateStatus(OrderStatus newStatus) {
        if (!this.status.canGoTo(newStatus)) throw new RuntimeException("Transição inválida de " + this.status + " para " + newStatus);
        this.status = newStatus;
    }

}
