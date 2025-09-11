package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.CategoryDto;
import com.saas.pos.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
  
  private final CategoryService categoryService;

  @PostMapping
  public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception{
    return ResponseEntity.ok(categoryService.createCategory(categoryDto));
  }

  @GetMapping("/store/{storeId}")
  public ResponseEntity<List<CategoryDto>> getCategoriesByStore(@PathVariable Long storeId) throws Exception{
    return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) throws Exception{
    return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) throws Exception{
    
    categoryService.deleteCategory(id);

    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Category Deleted Successfully");

    return ResponseEntity.ok(apiRes);
  }
}
