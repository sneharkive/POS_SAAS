package com.saas.pos.dto;

import lombok.Data;

@Data
public class AuthResponse {

  private String jwt;
  private String message;
  private UserDto user;
  
}
