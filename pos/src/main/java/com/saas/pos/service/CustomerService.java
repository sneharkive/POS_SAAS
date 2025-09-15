package com.saas.pos.service;

import java.util.List;

import com.saas.pos.model.Customer;

public interface CustomerService {
  
  Customer createCustomer (Customer customer) throws Exception;
  Customer updateCustomer (Long id, Customer customer) throws Exception;
  void deleteCustomer (Long id) throws Exception;
  Customer getCustomer (Long id) throws Exception;
  List<Customer> getAllCustomer () throws Exception;
  List<Customer> searchCustomers (String keyword) throws Exception;

}
