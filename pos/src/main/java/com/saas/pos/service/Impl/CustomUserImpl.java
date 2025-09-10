package com.saas.pos.service.Impl;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.saas.pos.model.User;
import com.saas.pos.repository.UserRepository;

@Service
public class CustomUserImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username);
    if(user == null) 
      throw new UsernameNotFoundException("User not found");

    GrantedAuthority authority = new SimpleGrantedAuthority(
      user.getRole().toString()
    );

    Collection<GrantedAuthority> authorities = Collections.singletonList(authority);
    
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

  }
  
}
