package com.saas.pos.service;

import java.util.List;

import com.saas.pos.dto.BranchDto;

public interface BranchService {

  BranchDto createBranch(BranchDto branchDto) throws Exception;
  BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception;
  void deleteBranch(Long id) throws Exception;
  List<BranchDto> getAllBranchesByStoreId(Long storeId) throws Exception;
  BranchDto getBranchById(Long id) throws Exception;


  
}
