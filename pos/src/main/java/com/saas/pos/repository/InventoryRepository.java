package com.saas.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saas.pos.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  
  Inventory findByProductIdAndBranchId(Long productId, Long branchId);

  List<Inventory> findByBranchId(Long branchId);
}
