package com.fahim.shoppingcard.request;

import com.fahim.shoppingcard.model.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class AddProductRequest {
    private long id;
    private String name;
    private BigDecimal price;
    private String brand;
    private int inventory;
    private String description;
    private Category category;
}
