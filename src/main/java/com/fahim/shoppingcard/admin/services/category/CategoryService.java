package com.fahim.shoppingcard.admin.services.category;

import com.fahim.shoppingcard.exceptions.AlradtExistExciption;
import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.admin.model.Category;
import com.fahim.shoppingcard.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category Not Found!")
                );
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c-> !categoryRepository.existsByName(c.getName())).map(categoryRepository::save).orElseThrow(()-> new AlradtExistExciption("This Already exist!"));
    }

    @Override
    public Category updateCategory(Category category ,Long id) {
        return Optional.ofNullable(findCategoryById(id)).map(oldCategory->{
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(()-> new ResourceNotFoundException("Category Not Found!"));

    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(
                categoryRepository::delete, ()-> {
                    throw new ResourceNotFoundException("Category Not Found!");
                }
        );
    }
}
