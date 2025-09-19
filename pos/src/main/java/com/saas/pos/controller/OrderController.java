package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.domain.OrderStatus;
import com.saas.pos.domain.PaymentType;
import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.OrderDto;
import com.saas.pos.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
    return ResponseEntity.ok(orderService.createOrder(orderDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @GetMapping("/branch/{branchId}")
  public ResponseEntity<List<OrderDto>> getOrdersByBranch(@PathVariable Long branchId,
      @RequestParam(required = false) Long customerId, @RequestParam(required = false) Long cashierId,
      @RequestParam(required = false) PaymentType paymentType, @RequestParam(required = false) OrderStatus orderStatus)
      throws Exception {
    return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, orderStatus));
  }

  @GetMapping("/cashier/{cashierId}")
  public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long cashierId) throws Exception {
    return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception {
    orderService.deleteOrder(id);

    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Order Deleted Successfully");

    return ResponseEntity.ok(apiRes);
  }

  @GetMapping("/today/branch/{branchId}")
  public ResponseEntity<List<OrderDto>> getTodayOrdersByBranch(@PathVariable Long branchId) throws Exception {
    return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
  }

  @GetMapping("/customer/{customerId}")
  public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable Long customerId) throws Exception {
    return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
  }

  @GetMapping("/recent/{branchId}")
  public ResponseEntity<List<OrderDto>> getTop5OrdersByBranchId(@PathVariable Long branchId) throws Exception {
    return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
  }

}
