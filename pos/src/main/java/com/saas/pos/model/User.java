package com.saas.pos.model;

import java.time.LocalDateTime;

import com.saas.pos.dto.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
  
  @Id
  private Long id;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false, unique = true)
  @Email(message = "Email should be valid")
  private String email;

  private String phone;

  @Column(nullable = false)
  private String password;

  private UserRole role;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lastLogin;
}
