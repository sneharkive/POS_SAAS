package com.saas.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.pos.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);
  
}
