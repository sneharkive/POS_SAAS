package com.saas.pos.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.InventoryDto;
import com.saas.pos.mapper.InventoryMapper;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Inventory;
import com.saas.pos.model.Product;
import com.saas.pos.repository.BranchRepository;
import com.saas.pos.repository.InventoryRepository;
import com.saas.pos.repository.ProductRepository;
import com.saas.pos.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;
  private final BranchRepository branchRepository;
  private final ProductRepository productRepository;


  @Override
  public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception{
    Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(() -> new Exception("No Branch Found"));

    Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(() -> new Exception("No Product Found"));

    Inventory inventory = InventoryMapper.toEntity(inventoryDto, branch, product);

    return InventoryMapper.toDTO(inventoryRepository.save(inventory));
  }

  @Override
  public InventoryDto updateInventory(Long id, InventoryDto inventoryDto)throws Exception {
    Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new Exception("No Inventory Found"));
    inventory.setQuantity(inventoryDto.getQuantity());

    return InventoryMapper.toDTO(inventoryRepository.save(inventory));
  }

  @Override
  public InventoryDto getInventoryById(Long id)throws Exception {
    Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new Exception("No Inventory Found"));
    return InventoryMapper.toDTO(inventory);
  }

  @Override
  public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId)throws Exception {
    Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);

    return InventoryMapper.toDTO(inventoryRepository.save(inventory));
  }

  @Override
  public List<InventoryDto> getAllInventoryByBranchId(Long branchId)throws Exception {
    List<Inventory> inventory = inventoryRepository.findByBranchId(branchId);
    return inventory.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public void deleteInventory(Long id)throws Exception {
    Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new Exception("No Inventory Found"));
    inventoryRepository.delete(inventory);
  }
  
}
