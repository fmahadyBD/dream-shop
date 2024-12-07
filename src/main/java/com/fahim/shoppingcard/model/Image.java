package com.fahim.shoppingcard.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
    private  String fileType;
    @Lob
    private Blob image;
    private String downloadUrl;

    @ManyToOne // many image can be one product's image;
    @JoinColumn(name = "product_id")
    private Product product;

}
