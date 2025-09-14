package com.saas.pos.service;

import java.util.List;

import com.saas.pos.dto.InventoryDto;

public interface InventoryService {

  InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
  InventoryDto updateInventory(Long id, InventoryDto inventoryDto) throws Exception;
  InventoryDto getInventoryById(Long id) throws Exception;
  InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) throws Exception;
  List<InventoryDto> getAllInventoryByBranchId(Long branchId) throws Exception;
  void deleteInventory(Long id) throws Exception;

  
}
