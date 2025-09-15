package com.saas.pos.dto;

import java.time.LocalDateTime;

import com.saas.pos.domain.UserRole;

import lombok.Data;

@Data
public class UserDto {

  private Long id;

  private String fullname;

  private String email;

  private String phone;

  private String password;

  private UserRole role;

  private Long branchId;
  private Long storeId;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lastLogin;

}
