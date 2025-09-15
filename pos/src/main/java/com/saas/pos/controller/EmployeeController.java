package com.saas.pos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.domain.UserRole;
import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.UserDto;
import com.saas.pos.model.User;
import com.saas.pos.service.EmployeeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping("/store/{storeId}")
  public ResponseEntity<UserDto> createStoreEmployee(@RequestBody UserDto userDto, @PathVariable Long storeId)
      throws Exception {

    return ResponseEntity.ok(employeeService.createStoreEmployee(userDto, storeId));

  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateEmployee(@RequestBody UserDto userDto, @PathVariable Long id) throws Exception {

    return ResponseEntity.ok(employeeService.updateEmployee(id, userDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception {
    employeeService.deleteEmployee(id);

    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Employee Deleted Successfully");

    return ResponseEntity.ok(apiRes);
  }

  @GetMapping("/store/{storeId}")
  public ResponseEntity<List<User>> findStoreEmployees(@PathVariable Long storeId, @RequestParam(required = false) UserRole userRole) throws Exception {
    
    return ResponseEntity.ok(employeeService.findStoreEmployees(storeId, userRole));
  }

  @GetMapping("/branch/{branchId}")
  public ResponseEntity<List<User>> findBranchEmployees(@PathVariable Long branchId, @RequestParam(required = false) UserRole userRole) throws Exception {
    
    return ResponseEntity.ok(employeeService.findBranchEmployees(branchId, userRole));
  }

}
