package com.fahim.shoppingcard.admin.services.category;

import com.fahim.shoppingcard.admin.model.Category;

import java.util.List;

public interface ICategoryService {
    Category findCategoryById(Long id);
    Category findCategoryByName(String name);
    List<Category> findAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category,Long id);
    void deleteCategory(Long id);

}
