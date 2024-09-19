package com.fahim.shoppingcard.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private int inventory;
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true) // it wll be remove when it's remove
    private List<Image> images;


    public Product(String name, BigDecimal price, String brand, int inventory, String description, Category category) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.inventory = inventory;
        this.description = description;
        this.category = category;
    }
}
