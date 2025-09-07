package com.saas.pos.service;

import com.saas.pos.dto.AuthResponse;
import com.saas.pos.dto.UserDto;
import com.saas.pos.exception.UserException;

public interface AuthService {
  AuthResponse signup(UserDto userDto)throws UserException;
  AuthResponse login(UserDto userDto) throws UserException;
}
