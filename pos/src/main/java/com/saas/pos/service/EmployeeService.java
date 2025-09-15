package com.saas.pos.service;

import java.util.List;

import com.saas.pos.domain.UserRole;
import com.saas.pos.dto.UserDto;
import com.saas.pos.model.User;

public interface EmployeeService {
  
  UserDto createStoreEmployee (UserDto employee, Long storeId) throws Exception;
  UserDto createBranchEmployee (UserDto employee, Long branchId) throws Exception;
  User updateEmployee (Long employeeId, UserDto employeeDetails) throws Exception;
  void deleteEmployee (Long employeeId) throws Exception;

  List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception;
  List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception;

}
