package com.saas.pos.mapper;

import com.saas.pos.dto.UserDto;
import com.saas.pos.model.User;

public class UserMapper {

  public static UserDto toDTO(User savedUser) {
    UserDto userDto = new UserDto();

    userDto.setId(savedUser.getId());
    userDto.setFullname(savedUser.getFullname());
    userDto.setEmail(savedUser.getEmail());
    userDto.setRole(savedUser.getRole());
    userDto.setPhone(savedUser.getPhone());
    userDto.setCreatedAt(savedUser.getCreatedAt());
    userDto.setUpdatedAt(savedUser.getUpdatedAt());
    userDto.setLastLogin(savedUser.getLastLogin());

    return userDto;
  }
  
}
