package com.saas.pos.dto;

import java.time.LocalDateTime;

import com.saas.pos.domain.PaymentType;
import com.saas.pos.model.Branch;
import com.saas.pos.model.ShiftReport;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefundDto {

  private Long id;

  private OrderDto order;

  private Long orderId;

  private String reason;

  private Double amount;

  private ShiftReport shiftReport;

  private Long shiftReportId;

  private UserDto cashier;

  private String cashierName;

  private BranchDto branch;

  private Long branchId;

  private PaymentType paymentType;

  private LocalDateTime createdAt;

}
