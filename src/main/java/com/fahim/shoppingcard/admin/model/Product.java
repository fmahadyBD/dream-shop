package com.fahim.shoppingcard.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"category", "images"})
@ToString(exclude = {"category", "images"})

/**
 * Since this class has bidirectional relationships, you should exclude these
 * relationships (category and images) from @ToString and @EqualsAndHashCode to
 * prevent infinite loops during serialization or debugging.
 * */
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private int inventory;

    @Column(length = 500)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    /**
     *
     * By default, @ManyToOne uses FetchType.EAGER, which may lead to performance issues when fetching
     * large datasets. It's better to explicitly set FetchType.LAZY for relationships to ensure data is
     * loaded only when needed.
     * */

public Product(String name, BigDecimal price, String brand, int inventory, String description, Category category) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
    }
}
