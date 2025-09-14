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
import com.saas.pos.dto.BranchDto;
import com.saas.pos.service.BranchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

  private final BranchService branchService;

  @PostMapping
  public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws Exception {
    return ResponseEntity.ok(branchService.createBranch(branchDto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
    return ResponseEntity.ok(branchService.getBranchById(id));
  }

  @GetMapping("/store/{storeId}")
  public ResponseEntity<List<BranchDto>> getAllBranchesByStoreId(@PathVariable Long storeId) throws Exception {
    return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BranchDto> updateBranch(@PathVariable Long id, @RequestBody BranchDto branchDto)
      throws Exception {
    return ResponseEntity.ok(branchService.updateBranch(id, branchDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
    branchService.deleteBranch(id);

    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setMessage("Branch Deleted Successfully");

    return ResponseEntity.ok(apiResponse);
  }

}
