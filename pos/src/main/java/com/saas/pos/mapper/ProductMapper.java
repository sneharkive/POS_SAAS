package com.saas.pos.mapper;

import com.saas.pos.dto.ProductDto;
import com.saas.pos.model.Product;
import com.saas.pos.model.Store;

public class ProductMapper {

  public static ProductDto toDTO(Product product) {
     return ProductDto.builder()
        .id(product.getId())
        .name(product.getName())
        .sku(product.getSku())
        .description(product.getDescription())
        .mrp(product.getMrp())
        .sellingPrice(product.getSellingPrice())
        .brand(product.getBrand())
        .image(product.getImage())
        .storeId(product.getStore() != null ? product.getStore().getId() : null)
        .createdAt(product.getCreatedAt())
        .updatedAt(product.getUpdatedAt())
        .build();
  }

  public static Product toEntity(ProductDto dto, Store store) {
    return Product.builder()
        .name(dto.getName())
        .sku(dto.getSku())
        .description(dto.getDescription())
        .mrp(dto.getMrp())
        .sellingPrice(dto.getSellingPrice())
        .brand(dto.getBrand())
        .image(dto.getImage())
        // .store(store)
        .build();
  }
}
