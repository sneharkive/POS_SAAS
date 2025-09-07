package com.saas.pos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saas.pos.config.JWtProvider;
import com.saas.pos.dto.AuthResponse;
import com.saas.pos.dto.UserDto;
import com.saas.pos.exception.UserException;
import com.saas.pos.model.User;
import com.saas.pos.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JWtProvider jWtProvider;
  private final CustomUserImpl customUserImpl;
  
  
  @Override
  public AuthResponse signup(UserDto userDto) throws UserException {
    User user = userRepository.findByEmail(userDto.getEmail());

    if(user != null)
      throw new UserException ("Email Id is already registered");
      
  }

  @Override
  public AuthResponse login(UserDto userDto)throws UserException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }
  
}
