package com.saas.pos.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class UserDto {


  private Long id;

  private String fullname;

  private String email;

  private String phone;

  private String password;

  private UserRole role;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lastLogin;
  
}
