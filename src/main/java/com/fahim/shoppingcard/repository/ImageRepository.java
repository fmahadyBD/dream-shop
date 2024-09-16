package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByProductId(long product_id);
}
