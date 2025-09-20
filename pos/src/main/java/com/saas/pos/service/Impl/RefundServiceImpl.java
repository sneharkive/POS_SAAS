package com.saas.pos.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.RefundDto;
import com.saas.pos.mapper.RefundMapper;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Order;
import com.saas.pos.model.Refund;
import com.saas.pos.model.User;
import com.saas.pos.repository.OrderRepository;
import com.saas.pos.repository.RefundRepository;
import com.saas.pos.service.RefundService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

  private final RefundRepository refundRepository;
  private final UserService userService;
  private final OrderRepository orderRepository;

  @Override
  public RefundDto createRefund(RefundDto refundDto) throws Exception {

    User cashier = userService.getCurrentUser();

    Order order = orderRepository.findById(refundDto.getOrderId())
        .orElseThrow(() -> new Exception("Order Not Found!!"));

    Branch branch = order.getBranch();

    Refund refund = Refund.builder()
        .order(order)
        .cashier(cashier)
        .branch(branch)
        .reason(refundDto.getReason())
        .amount(refundDto.getAmount())
        .createdAt(refundDto.getCreatedAt())
        .build();

    return RefundMapper.toDTO(refundRepository.save(refund));
  }

  @Override
  public List<RefundDto> getAllRefund() throws Exception {
    return refundRepository.findAll().stream().map(RefundMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<RefundDto> getRefundByCashier(Long cashierId) throws Exception {
    return refundRepository.findByCashierId(cashierId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception {

    return refundRepository.findByShiftReportId(shiftReportId).stream().map(RefundMapper::toDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate)
      throws Exception {

    return refundRepository.findByCashierIdAndCreatedAtBetween(cashierId, startDate, endDate).stream()
        .map(RefundMapper::toDTO).collect(Collectors.toList());

  }

  @Override
  public List<RefundDto> getRefundByBranch(Long branchId) throws Exception {
    
    return refundRepository.findByBranchId(branchId).stream().map(RefundMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public RefundDto getRefundByID(Long refundId) throws Exception {

    return RefundMapper.toDTO(refundRepository.findById(refundId).orElseThrow(() -> new Exception("No Refund Found")));
  }

  @Override
  public void deleteRefund(Long refundId) throws Exception {
    
    this.getRefundByID(refundId);

    refundRepository.deleteById(refundId);
  }
}
