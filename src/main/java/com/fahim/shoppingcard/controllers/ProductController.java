package com.fahim.shoppingcard.controllers;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.fahim.shoppingcard.dto.ProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fahim.shoppingcard.model.Product;
import com.fahim.shoppingcard.request.AddProductRequest;
import com.fahim.shoppingcard.request.UpdateProductRequest;
import com.fahim.shoppingcard.response.ApiResponse;
import com.fahim.shoppingcard.services.product.IProductService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest addProductRequest) {
        try{
            Product product = productService.addProduct(addProductRequest);
            ProductDto productDto = productService.getConvertedToDto(product);
            return ResponseEntity.ok().body(new ApiResponse("Added new Product",productDto));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error",e.getMessage()));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductById(@PathVariable Long productId) {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.ok().body(new ApiResponse("Deleted product", productId));
        }catch (Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProductById(@PathVariable Long productId, @RequestBody UpdateProductRequest request) {
        try{
            Product updateProduct=productService.updateProduct(request,productId);
            ProductDto productDto = productService.getConvertedToDto(updateProduct);
            return ResponseEntity.ok().body(new ApiResponse("Update successful ",productDto));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try{
            List<Product> products = productService.getAllProducts();
            List<ProductDto> productDtoList = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Found! ", productDtoList));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }

    }

    @GetMapping("/product/{ProductId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long ProductId) {

        try{
            Product product = productService.getProductById(ProductId);
            ProductDto productDto = productService.getConvertedToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found! ",productDto));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductsByName(@PathVariable String name) {
        try{
            List<Product> products = productService.getProductsByName(name);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Found! ", products));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }
    }

    //check
    @GetMapping("/products/category")
    public ResponseEntity<ApiResponse> getProductsByCategory(@RequestParam String category) {
        try{
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            List<ProductDto> productDtoList = productService.getConvertedProducts(products);
            return ResponseEntity.ok(new ApiResponse("Found! ", productDtoList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));

        }
    }

    @GetMapping("/products/brand")
    public ResponseEntity<ApiResponse> getProductsByBrand(@RequestParam String brand) {
        try{
            List<Product> products = productService.getProductsByBrand(brand);
            if(products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Found! ", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));

        }
    }

    @GetMapping("/products/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try{
            List<Product> productList = productService.getProductsByBrandAndName(brand,name);
            if(productList.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            List<ProductDto> productDtoList = productService.getConvertedProducts(productList);
            return ResponseEntity.ok(new ApiResponse("Found! ", productDtoList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @GetMapping("/products/category-and-name")
    public ResponseEntity<ApiResponse> getProductsByCategoryAndBrand(@RequestParam String category, @RequestParam String name) {
        try{
            List<Product> productList = productService.getProductsByCategoryAndBrand(category,name);
            if(productList.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            List<ProductDto> productDtoList = productService.getConvertedProducts(productList);
            return ResponseEntity.ok(new ApiResponse("Found! ", productDtoList));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }
    }

    @GetMapping("/product/count/by-category")
    public ResponseEntity<ApiResponse> countProductsByCategory(@RequestParam String category) {
        try{
            Long count = productService.countProductsByCategory(category);
            if(count<=0){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Found! ", count));

        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }

    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try{
            Long product =productService.countProductsByBrandAndName(brand,name);
            if(product<=0){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Product not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Found! ", product));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse( e.getMessage(),null));
        }

    }




}
