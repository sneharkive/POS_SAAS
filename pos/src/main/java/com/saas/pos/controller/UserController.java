package com.saas.pos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.UserDto;
import com.saas.pos.exception.UserException;
import com.saas.pos.mapper.UserMapper;
import com.saas.pos.model.User;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/profile")
  public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String jwt) throws UserException {
    User user = userService.getUserFromJwtToken(jwt);
    return ResponseEntity.ok(UserMapper.toDTO(user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws UserException {
    User user = userService.getUserById(id);

    return ResponseEntity.ok(UserMapper.toDTO(user));
  }


  @GetMapping
  public ResponseEntity<UserDto> getCurrentUser() throws UserException {
    User user = userService.getCurrentUser();

    return ResponseEntity.ok(UserMapper.toDTO(user));
  }
  
}
