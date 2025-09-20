package com.saas.pos.mapper;

import com.saas.pos.dto.OrderItemDto;
import com.saas.pos.model.OrderItem;

public class OrderItemMapper {

  public static OrderItemDto toDTO(OrderItem item){
    if(item == null) return null;

    return OrderItemDto.builder()
            .id(item.getId())
            .productId(item.getProduct().getId())
            .quantity(item.getQuantity())
            .price(item.getPrice())
            .orderId(item.getOrder() != null ? item.getOrder().getId() : null)
            .product(ProductMapper.toDTO(item.getProduct()))
            .build();
  }
  
}
