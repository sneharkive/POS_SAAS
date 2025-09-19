package com.saas.pos.mapper;

import java.util.stream.Collectors;

import com.saas.pos.dto.OrderDto;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Order;
import com.saas.pos.model.Product;

public class OrderMapper {
   public static OrderDto toDTO(Order order){
    if (order == null) return null;

    return OrderDto.builder()
            .id(order.getId())
            .totalAmount(order.getTotalAmount())
            .branchId(order.getBranch() != null ? order.getBranch().getId() : null)
            .cashier(UserMapper.toDTO(order.getCashier()))
            .customer(order.getCustomer())
            .paymentType(order.getPaymentType())
            .createdAt(order.getCreatedAt())
            .items(order.getItems().stream().map(OrderItemMapper::toDTO).collect(Collectors.toList()))
            .build();
  }


  public static Order toEntity(OrderDto orderDto, Branch branch, Product product){
    return Order.builder()
            .id(orderDto.getId())
            .branch(branch)
            .build();
  }
}
