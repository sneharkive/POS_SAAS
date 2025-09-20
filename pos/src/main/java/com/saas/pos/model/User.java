package com.saas.pos.model;

import java.time.LocalDateTime;

import com.saas.pos.domain.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String fullname;

  @Column(nullable = false, unique = true)
  @Email(message = "Email should be valid")
  private String email;

  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store;

  @ManyToOne
  @JoinColumn(name = "branch_id")
  private Branch branch;

  private String phone;

  @Column(nullable = false)
  private String password;

  private UserRole role;

// @Column(name = "branch_id", insertable = false, updatable = false)
//   private Long branchId;

//   @Column(name = "store_id", insertable = false, updatable = false)
//   private Long storeId;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime lastLogin;
}
