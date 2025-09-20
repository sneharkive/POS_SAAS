package com.saas.pos.service;

import java.time.LocalDateTime;
import java.util.List;

import com.saas.pos.dto.RefundDto;

public interface RefundService {
  
  RefundDto createRefund(RefundDto refundDto) throws Exception;
  List<RefundDto> getAllRefund() throws Exception;
  List<RefundDto> getRefundByCashier(Long cashierId) throws Exception;
  List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception;
  List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
  List<RefundDto> getRefundByBranch(Long branchId) throws Exception;
  RefundDto getRefundByID(Long refundId) throws Exception;
  void deleteRefund(Long refundId) throws Exception;

}
