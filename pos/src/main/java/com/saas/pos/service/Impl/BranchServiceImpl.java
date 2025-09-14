package com.saas.pos.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.BranchDto;
import com.saas.pos.mapper.BranchMapper;
import com.saas.pos.model.Branch;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;
import com.saas.pos.repository.BranchRepository;
import com.saas.pos.repository.StoreRepository;
import com.saas.pos.service.BranchService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

  private final BranchRepository branchRepository;
  private final StoreRepository storeRepository;
  private final UserService userService;
  
  @Override
  public BranchDto createBranch(BranchDto branchDto, String jwt) throws Exception {
    User currUser = userService.getUserFromJwtToken(jwt);
    System.out.println(currUser);
    Store store = storeRepository.findByStoreAdminId(currUser.getId());

    Branch branch = BranchMapper.toEntity(branchDto, store);
    return BranchMapper.toDTO(branchRepository.save(branch));
  }

  @Override
  public BranchDto updateBranch(Long id, BranchDto branchDto) throws Exception {

    Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("No Branch Found"));
    
    existing.setName(branchDto.getName());
    existing.setWorkingDays(branchDto.getWorkingDays());
    existing.setEmail(branchDto.getEmail());
    existing.setPhone(branchDto.getPhone());
    existing.setAddress (branchDto.getAddress ());
    existing.setOpenTime(branchDto.getOpenTime());
    existing.setCloseTime(branchDto.getCloseTime());
    existing.setUpdatedAt(LocalDateTime.now());

    return BranchMapper.toDTO(branchRepository.save(existing));

  }

  @Override
  public void deleteBranch(Long id) throws Exception {
    Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("No Branch Found"));

    branchRepository.delete(existing);
  }

  @Override
  public List<BranchDto> getAllBranchesByStoreId(Long storeId) throws Exception {
    List<Branch> branches = branchRepository.findByStoreId(storeId);
    return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
  }
  
  @Override
  public BranchDto getBranchById(Long id) throws Exception {
    Branch existing = branchRepository.findById(id).orElseThrow(() -> new Exception("No Branch Found"));

    return BranchMapper.toDTO(existing);
  }

}
