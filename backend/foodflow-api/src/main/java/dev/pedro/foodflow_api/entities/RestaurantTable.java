package dev.pedro.foodflow_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tables")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID publicId;

    @Column(nullable = false, unique = true)
    private Integer number;

    @Builder.Default
    @Column(nullable = false)
    private boolean free = true;

    @OneToMany(mappedBy = "table")
    private List<Order> orders = new ArrayList<>();

    @PrePersist
    public void generateUUID() {
        if (publicId == null) publicId = UUID.randomUUID();
    }

}
