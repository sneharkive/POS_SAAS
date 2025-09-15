package com.saas.pos.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.pos.model.Customer;
import com.saas.pos.repository.CustomerRepository;
import com.saas.pos.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  
  @Override
  public Customer createCustomer(Customer customer) throws Exception {
    return customerRepository.save(customer);
  }

  @Override
  public Customer updateCustomer(Long id, Customer customer) throws Exception {
    Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer Not Found"));
    customerToUpdate.setFullName(customer.getFullName());
    customerToUpdate.setEmail(customer.getEmail());
    customerToUpdate.setPhone(customer.getPhone());
    customerToUpdate.setUpdatedAt(LocalDateTime.now());

    return customerRepository.save(customerToUpdate);
  }

  @Override
  public void deleteCustomer(Long id) throws Exception {
    Customer cus = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer Not Found"));
    customerRepository.delete(cus);
  }

  @Override
  public Customer getCustomer(Long id) throws Exception {

    return customerRepository.findById(id).orElseThrow(() -> new Exception("Customer Not Found"));
  }

  @Override
  public List<Customer> getAllCustomer() throws Exception {

    return customerRepository.findAll();
  }

  @Override
  public List<Customer> searchCustomers(String keyword) throws Exception {
    
    return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
  }
  
}
