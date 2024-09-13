package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
