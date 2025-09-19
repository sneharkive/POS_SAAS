package com.saas.pos.service.Impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.domain.OrderStatus;
import com.saas.pos.domain.PaymentType;
import com.saas.pos.dto.OrderDto;
import com.saas.pos.mapper.OrderMapper;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Order;
import com.saas.pos.model.OrderItem;
import com.saas.pos.model.Product;
import com.saas.pos.model.User;
import com.saas.pos.repository.OrderRepository;
import com.saas.pos.repository.ProductRepository;
import com.saas.pos.service.OrderService;
import com.saas.pos.service.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final UserService userService;
  private final ProductRepository productRepository;

  @Override
  public OrderDto createOrder(OrderDto orderDto) throws Exception {
    
    User cashier = userService.getCurrentUser();

    Branch branch = cashier.getBranch();
    if (branch == null)
      throw new Exception("Cashier's Branch Not Found");

    Order order = Order.builder()
        .branch(branch)
        .cashier(cashier)
        .customer(orderDto.getCustomer())
        .paymentType(orderDto.getPaymentType())
        .build();

    List<OrderItem> orderItems = orderDto.getItems().stream().map(
        itemDto -> {
          Product product = productRepository.findById(itemDto.getProductId())
              .orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

          return OrderItem.builder()
              .product(product)
              .quantity(itemDto.getQuantity())
              .price(product.getSellingPrice() * itemDto.getQuantity())
              .order(order)
              .build();
        }).toList();

    double total = orderItems.stream().mapToDouble(
        OrderItem::getPrice).sum();
    order.setTotalAmount(total);
    order.setItems(orderItems);

    return OrderMapper.toDTO(orderRepository.save(order));
  }

  @Override
  public OrderDto getOrderById(Long id) throws Exception {

    return orderRepository.findById(id).map(OrderMapper::toDTO)
        .orElseThrow(() -> new Exception("Order Not Found with Id " + id));
  }

  @Override
  public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType,
      OrderStatus orderStatus) throws Exception {

    return orderRepository.findByBranchId(branchId).stream().filter(
        order -> customerId == null || (order.getCustomer() != null && order.getCustomer().getId().equals(customerId)))
        .filter(order -> cashierId == null || order.getCashier() != null && order.getCashier().getId().equals(cashierId))
        .filter(order -> paymentType == null || order.getPaymentType()==paymentType)
        .map(OrderMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<OrderDto> getOrderByCashier(Long cashierId) {

    return orderRepository.findByCashierId(cashierId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public void deleteOrder(Long id) throws Exception {
    
    Order order = orderRepository.findById(id).orElseThrow(() -> new Exception("Order Not Found with id " + id));
    orderRepository.delete(order);
  }

  @Override
  public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {

    LocalDate today = LocalDate.now();
    LocalDateTime start = today.atStartOfDay();
    LocalDateTime end = today.plusDays(1).atStartOfDay();

    return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start, end).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {

    return orderRepository.findByCustomerId(customerId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {

    return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream().map(OrderMapper::toDTO).collect(Collectors.toList());
  }

}
