package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.InventoryDto;
import com.saas.pos.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {
  
  private final InventoryService inventoryService;

  @PostMapping
  public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) throws Exception{
    return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) throws Exception{
    return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) throws Exception{
    inventoryService.deleteInventory(id);

    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Inventory Deleted Successfully");

    return ResponseEntity.ok(apiRes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) throws Exception{
    return ResponseEntity.ok(inventoryService.getInventoryById(id));
  }
  
  @GetMapping("/branch/{branchId}/product/{productId}")
  public ResponseEntity<InventoryDto> getInventoryByProductIdAndBranchId( @PathVariable Long branchId, @PathVariable Long productId) throws Exception{
    return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId, branchId));
  }
  
  @GetMapping("/branch/{branchId}")
  public ResponseEntity<List<InventoryDto>> getAllInventoryByBranchId(@PathVariable Long branchId) throws Exception{
    return ResponseEntity.ok(inventoryService.getAllInventoryByBranchId(branchId));
  }
}
