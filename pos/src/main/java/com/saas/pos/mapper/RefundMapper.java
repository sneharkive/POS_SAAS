package com.saas.pos.mapper;

import com.saas.pos.dto.RefundDto;
import com.saas.pos.model.Refund;

public class RefundMapper {
  
  public static RefundDto toDTO(Refund refund) {
     return RefundDto.builder()
        .id(refund.getId())
        .amount(refund.getAmount())
        .order(OrderMapper.toDTO(refund.getOrder()))
        .orderId(refund.getOrder() != null ? refund.getOrder().getId() : null)
        .reason(refund.getReason())
        .cashier(UserMapper.toDTO(refund.getCashier()))
        .cashierName(refund.getCashier().getFullname())
        .branch(BranchMapper.toDTO(refund.getBranch()))
        .branchId(refund.getBranch() != null ? refund.getBranch().getId() : null)
        .shiftReportId(refund.getShiftReport() != null ? refund.getShiftReport().getId() : null)
        .createdAt(refund.getCreatedAt())
        .paymentType(refund.getPaymentType())
        .build();
  }

}
