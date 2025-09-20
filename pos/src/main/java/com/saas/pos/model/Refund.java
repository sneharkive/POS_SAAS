package com.saas.pos.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saas.pos.domain.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Order order;

  private String reason;

  private Double amount;

  @ManyToOne
  @JsonIgnore
  private ShiftReport shiftReport;

  @ManyToOne
  private User cashier;

  @ManyToOne
  private Branch branch;

  private PaymentType paymentType;

  private LocalDateTime createdAt;

  @PrePersist
  protected void onCreated(){
    createdAt = LocalDateTime.now();
  }

}
