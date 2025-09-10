package com.saas.pos.service.Impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.saas.pos.config.JWtProvider;
import com.saas.pos.exception.UserException;
import com.saas.pos.model.User;
import com.saas.pos.repository.UserRepository;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JWtProvider jWtProvider;

  @Override
  public User getUserFromJwtToken(String token) throws UserException {
    String email = jWtProvider.getEmailFromToken(token);
    User user = userRepository.findByEmail(email);
    
    if(user == null) throw new UserException("Invalid Token");

    return user;
  }

  @Override
  public User getCurrentUser() throws UserException {
    String email = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.findByEmail(email);

    if(user == null) throw new UserException("User not found");

    return user;
  }

  @Override
  public User getUserByEmail(String email) throws UserException {
    User user = userRepository.findByEmail(email);

    if(user == null) throw new UserException("User not found");

    return user;
  }

  @Override
  public User getUserById(Long id) throws UserException {
    return userRepository.findById(id).orElseThrow(() -> new UserException("No User find with Id : " + id));
  }

  @Override
  public List<User> getAllUsers() throws UserException {
    return userRepository.findAll();
  }
  
}
