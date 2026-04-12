package dev.pedro.foodflow_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "products")
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String imageUrl;

    @Builder.Default
    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
