package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.ApiResponse;
import com.saas.pos.model.Customer;
import com.saas.pos.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping()
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer cus) throws Exception {
    return ResponseEntity.ok(customerService.createCustomer(cus));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer cus) throws Exception {
    return ResponseEntity.ok(customerService.updateCustomer(id, cus));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(customerService.getCustomer(id));
  }

  @GetMapping()
  public ResponseEntity<List<Customer>> getAllCustomer() throws Exception {
    return ResponseEntity.ok(customerService.getAllCustomer());
  }

  @GetMapping("/search")
  public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String q) throws Exception {
    return ResponseEntity.ok(customerService.searchCustomers(q));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception {
    customerService.deleteCustomer(id);
    
    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Customer Deleted Successfully");
    return ResponseEntity.ok(apiRes);
  }

}
