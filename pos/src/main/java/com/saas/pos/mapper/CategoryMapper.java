package com.saas.pos.mapper;

import com.saas.pos.dto.CategoryDto;
import com.saas.pos.model.Category;

public class CategoryMapper {
  
  public static CategoryDto toDTO (Category category){
    return CategoryDto.builder()
            .id(category.getId())
            .name(category.getName())
            .storeId(category.getStore() != null?category.getStore().getId():null)
            .build();
  }


  // public static Category toEntity (CategoryDto category){
    
  // }
}
