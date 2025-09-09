package com.saas.pos.dto;

import java.time.LocalDateTime;

import com.saas.pos.model.StoreContact;

import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class StoreDto {
  private Long id;

  private String brand;

  private UserDto storeAdmin;

  
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private String description;

  private String storeType;

  private StoreStatus status;

  private StoreContact contact;
}
