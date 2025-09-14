package com.saas.pos.mapper;

import com.saas.pos.dto.InventoryDto;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Inventory;
import com.saas.pos.model.Product;

public class InventoryMapper {

  public static InventoryDto toDTO(Inventory inv){
    return InventoryDto.builder()
            .id(inv.getId())
            .branchId(inv.getBranch() != null ? inv.getBranch().getId() : null)
            .branch(BranchMapper.toDTO(inv.getBranch()))
            .productId(inv.getProduct() != null ? inv.getProduct().getId() : null)
            .product(ProductMapper.toDTO(inv.getProduct()))
            .quantity(inv.getQuantity())
            .lastUpdate(inv.getLastUpdate())
            .build();
  }


  public static Inventory toEntity(InventoryDto inv, Branch branch, Product product){
    return Inventory.builder()
            .id(inv.getId())
            .branchId(branch.getId())
            .branch(branch)
            .productId(product.getId())
            .product(product)
            .quantity(inv.getQuantity())
            .lastUpdate(inv.getLastUpdate())
            .build();
  }
  
}
