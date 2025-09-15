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
    userDto.setStoreId(savedUser.getStore() != null ? savedUser.getStore().getId() : null);
    userDto.setBranchId(savedUser.getBranch() != null ? savedUser.getBranch().getId() : null);
    return userDto;
  }
  
  public static User toEntity(UserDto userDto){
    User user = new User();

    user.setId(userDto.getId());
    user.setFullname(userDto.getFullname());
    user.setEmail(userDto.getEmail());
    user.setRole(userDto.getRole());
    user.setPhone(userDto.getPhone());
    user.setPassword(userDto.getPassword());
    user.setCreatedAt(userDto.getCreatedAt());
    user.setUpdatedAt(userDto.getUpdatedAt());
    user.setLastLogin(userDto.getLastLogin());
    if(userDto.getBranchId() != null)userDto.setBranchId(userDto.getBranchId());
    if(userDto.getStoreId() != null)userDto.setStoreId(userDto.getStoreId());

    return user;
  }
}
