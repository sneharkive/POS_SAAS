package com.saas.pos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
  @JoinColumn(name = "branch_id", nullable = false)
  private Branch branch;

  // @Column(name = "branch_id", insertable = false, updatable = false)
  // private Long branchId;
  
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;
  
  // @Column(name = "product_id", insertable = false, updatable = false)
  // private Long productId;

  @Column(nullable = false)
  private Integer quantity;

  private LocalDateTime lastUpdate;

  @PrePersist
  @PreUpdate
  protected void lastUpdate(){
    lastUpdate = LocalDateTime.now();
  }

}
