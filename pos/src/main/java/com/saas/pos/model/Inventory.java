package com.saas.pos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  @ManyToOne
  private Branch branch;

  private Long branchId;
  
  @ManyToOne
  private Product product;
  
  private Long productId;

  @Column(nullable = false)
  private Integer quantity;

  private LocalDateTime lastUpdate;

  @PrePersist
  @PreUpdate
  protected void lastUpdate(){
    lastUpdate = LocalDateTime.now();
  }

}
