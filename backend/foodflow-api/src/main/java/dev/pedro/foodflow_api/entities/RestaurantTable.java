package dev.pedro.foodflow_api.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private Integer number;

    @Builder.Default
    @Column(nullable = false)
    private boolean free = true;

    @Builder.Default
    @OneToMany(mappedBy = "table")
    private List<Order> orders = new ArrayList<>();

}
