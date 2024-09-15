package com.fahim.shoppingcard.controllers;

import com.fahim.shoppingcard.exceptions.AlradtExistExciption;
import com.fahim.shoppingcard.exceptions.ResourceNotFoundException;
import com.fahim.shoppingcard.model.Category;
import com.fahim.shoppingcard.response.ApiResponse;
import com.fahim.shoppingcard.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        try{
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Add Category Successfully",theCategory));
        }catch (AlradtExistExciption e){
            // in service ,we check the category already exist or not that's why use the CONFLICT
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {
        try{
                List<Category> categories= categoryService.findAllCategories();
                return ResponseEntity.ok(new ApiResponse("found",categories));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error! ",INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/category/{categoryId}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable  Long categoryId) {
        try{
            Category category = categoryService.findCategoryById(categoryId);
            return ResponseEntity.ok(new ApiResponse("found",category));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/category/{categoryName}/category")
    public ResponseEntity<ApiResponse> getCategoryByeName(@PathVariable String categoryName){
        try{
            Category thecategory = categoryService.findCategoryByName(categoryName);
            return ResponseEntity.ok(new ApiResponse("found",thecategory));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @DeleteMapping("/category/{categoryId}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long categoryId){
        try{
            categoryService.deleteCategory(categoryId);
            return ResponseEntity.ok(new ApiResponse("Delete Category Successfully",null));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("category/{categoryId}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
        try{
            Category updateCategory = categoryService.updateCategory(category,categoryId);
            return ResponseEntity.ok(new ApiResponse("Update Category Successfully",updateCategory));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
