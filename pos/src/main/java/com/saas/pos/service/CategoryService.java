package com.saas.pos.service;

import java.util.List;

import com.saas.pos.dto.CategoryDto;

public interface CategoryService {
  
  CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
  List<CategoryDto> getCategoriesByStore(Long storeId) throws Exception;
  CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception;
  void deleteCategory(Long id) throws Exception;

}
