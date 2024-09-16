package com.fahim.shoppingcard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @JsonIgnore // it will break the loop
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(String name) {
    this.name = name;
    }

}
