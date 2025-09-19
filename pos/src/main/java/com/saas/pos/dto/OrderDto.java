package com.saas.pos.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.saas.pos.domain.PaymentType;
import com.saas.pos.model.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

  private Long id;

  private Double totalAmount;

  private LocalDateTime createdAt;

  private Long branchId;
  private Long customerId;

  private BranchDto branch;

  private UserDto cashier;

  private Customer customer;

  private List<OrderItemDto> items;

  private PaymentType paymentType;

}
