package com.saas.pos.service;

import java.util.List;

import com.saas.pos.dto.BranchDto;
import com.saas.pos.model.User;

public interface BranchService {

  BranchDto createBranch(BranchDto branchDto, User user) throws Exception;
  BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception;
  void deleteBranch(Long id) throws Exception;
  BranchDto getBranchByStoreId(Long id) throws Exception;
  List<BranchDto> getAllBranchesByStoreId(Long storeId) throws Exception;


  
}
