package com.saas.pos.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CategoryDto {

  private Long id;

  private String name;

  // private Store store;

  private Long storeId;
  
}
