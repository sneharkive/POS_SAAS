package com.saas.pos.service.Impl;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saas.pos.config.JWtProvider;
import com.saas.pos.domain.UserRole;
import com.saas.pos.dto.AuthResponse;
import com.saas.pos.dto.UserDto;
import com.saas.pos.exception.UserException;
import com.saas.pos.mapper.UserMapper;
import com.saas.pos.model.User;
import com.saas.pos.repository.UserRepository;
import com.saas.pos.service.AuthService;

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
    if (user != null)
      throw new UserException("Email Id is already registered");

    if (userDto.getRole().equals(UserRole.ROLE_ADMIN))
      throw new UserException("Role Admin is not allowed!!");

    User newUser = new User();
    newUser.setEmail(userDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
    newUser.setRole(userDto.getRole());
    newUser.setFullname(userDto.getFullname());
    newUser.setPhone(userDto.getPhone());
    newUser.setLastLogin(LocalDateTime.now());
    newUser.setCreatedAt(LocalDateTime.now());
    newUser.setUpdatedAt(LocalDateTime.now());

    User savedUser = userRepository.save(newUser);

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jWtProvider.generateToken(authentication);

    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("Register Successfully");
    authResponse.setUser(UserMapper.toDTO(savedUser));

    return authResponse;

  }

  @Override
  public AuthResponse login(UserDto userDto) throws UserException {
    Authentication authentication = authenticate(userDto.getEmail(), userDto.getPassword());

    SecurityContextHolder.getContext().setAuthentication(authentication);
    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
    String role = authorities.iterator().next().getAuthority();

    String jwt = jWtProvider.generateToken(authentication);

    User user = userRepository.findByEmail(userDto.getEmail());
    user.setLastLogin(LocalDateTime.now());
    userRepository.save(user);

    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("Login Successfully");
    authResponse.setUser(UserMapper.toDTO(user));

    return authResponse;
  }

  private Authentication authenticate(String email, String password) throws UserException {

    UserDetails userDetails = customUserImpl.loadUserByUsername(email);
    if(userDetails == null)
      throw  new UserException("User does not exist with this email ID : " + email);
    
      if(!passwordEncoder.matches(password, userDetails.getPassword()))
        throw new UserException("Password does not match!!");
        
    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }

}
