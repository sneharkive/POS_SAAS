package com.saas.pos.service;

import java.util.List;

import com.saas.pos.domain.OrderStatus;
import com.saas.pos.domain.PaymentType;
import com.saas.pos.dto.OrderDto;

public interface OrderService {

  OrderDto createOrder(OrderDto orderDto) throws Exception;
  OrderDto getOrderById(Long id) throws Exception;
  List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception;
  List<OrderDto> getOrderByCashier(Long cashierId);
  void deleteOrder(Long id) throws Exception;
  List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;
  List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception;
  List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
}
