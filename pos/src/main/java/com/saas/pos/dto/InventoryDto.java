package com.saas.pos.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDto {
  
  private Long id;
  
  private BranchDto branch;
  private Long branchId;

  private ProductDto product;
  private Long productId;

  private Integer quantity;

  private LocalDateTime lastUpdate;
}
