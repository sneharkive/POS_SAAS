package com.saas.pos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.AuthResponse;
import com.saas.pos.dto.UserDto;
import com.saas.pos.exception.UserException;
import com.saas.pos.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<AuthResponse> signupHandler(@RequestBody UserDto userDto) throws UserException {

    return ResponseEntity.ok(authService.signup(userDto));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> loginHandler(@RequestBody UserDto userDto) throws UserException {


    return ResponseEntity.ok(authService.login(userDto));
  }

}
