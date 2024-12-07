package com.fahim.shoppingcard.admin.repository;

import com.fahim.shoppingcard.admin.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
    boolean existsByName(String name);
}
