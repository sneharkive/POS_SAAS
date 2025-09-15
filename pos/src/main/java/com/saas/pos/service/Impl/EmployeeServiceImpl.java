package com.saas.pos.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saas.pos.domain.UserRole;
import com.saas.pos.dto.UserDto;
import com.saas.pos.mapper.UserMapper;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;
import com.saas.pos.repository.BranchRepository;
import com.saas.pos.repository.StoreRepository;
import com.saas.pos.repository.UserRepository;
import com.saas.pos.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final UserRepository userRepository;
  private final StoreRepository storeRepository;
  private final BranchRepository branchRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
    Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));

    Branch branch = null;

    if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
      if (employee.getBranchId() == null)
        throw new Exception("Branch Id is required to create Branch Manager");

      branch = branchRepository.findById(employee.getBranchId()).orElseThrow(() -> new Exception("Branch not Found"));
    }

    User user = UserMapper.toEntity(employee);
    user.setStore(store);
    user.setBranch(branch);
    user.setPassword(passwordEncoder.encode(employee.getPassword()));
    User savedEmployee = userRepository.save(user);

    if (employee.getRole() == UserRole.ROLE_BRANCH_MANAGER && branch != null) {
      branch.setManager(savedEmployee);
      branchRepository.save(branch);
    }

    return UserMapper.toDTO(savedEmployee);
  }

  @Override
  public UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception {
    Branch branch = branchRepository.findById(branchId)
        .orElseThrow(() -> new Exception("Branch not Found"));

    if (employee.getRole() == UserRole.ROLE_BRANCH_CASHIER || employee.getRole() == UserRole.ROLE_BRANCH_MANAGER) {
      User user = UserMapper.toEntity(employee);
      user.setBranch(branch);
      user.setPassword(passwordEncoder.encode(employee.getPassword()));
      return UserMapper.toDTO(userRepository.save(user));
    }

    throw new Exception("Branch role is not supported");
  }

  @Override
  public User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
    User existingEmp = userRepository.findById(employeeId)
        .orElseThrow(() -> new Exception("Employee does not exist with given id"));

    Branch branch = branchRepository.findById(employeeDetails.getBranchId())
        .orElseThrow(() -> new Exception("Branch not found"));

    existingEmp.setEmail(employeeDetails.getEmail());
    existingEmp.setFullname(employeeDetails.getFullname());
    existingEmp.setPassword(employeeDetails.getPassword());
    existingEmp.setRole(employeeDetails.getRole());
    existingEmp.setBranch(branch);

    return userRepository.save(existingEmp);
  }

  @Override
  public void deleteEmployee(Long employeeId) throws Exception {
    User user = userRepository.findById(employeeId)
        .orElseThrow(() -> new Exception("Employee does not exist with given id"));

    userRepository.delete(user);
  }

  @Override
  public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
    Store store = storeRepository.findById(storeId).orElseThrow(() -> new Exception("Store not found"));

    return userRepository.findByStore(store).stream().filter(
      user -> role == null || user.getRole() == role
    ).map(UserMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<UserDto> findBranchEmployees(Long branchId, UserRole role) throws Exception {
    Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not Found"));

    return userRepository.findByBranchId(branchId).stream().filter(
      user -> role == null || user.getRole() == role
    ).map(UserMapper::toDTO).collect(Collectors.toList());

  }

}
