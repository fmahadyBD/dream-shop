package com.fahim.shoppingcard.repository;

import com.fahim.shoppingcard.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
Category findByName(String name);
}
