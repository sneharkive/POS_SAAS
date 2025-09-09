package com.saas.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.pos.exception.UserException;
import com.saas.pos.model.User;

@Service
public interface UserService {
  User getUserFromJwtToken(String token) throws UserException;
  User getCurrentUser() throws UserException ;
  User getUserByEmail(String email) throws UserException ;
  User getUserById(Long id) throws UserException ;
  List<User> getAllUsers() throws UserException ;
}
