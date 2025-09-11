package com.saas.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.BranchDto;
import com.saas.pos.service.BranchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

  private final BranchService branchService;

  @PostMapping
  public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws Exception{
    return ResponseEntity.ok(branchService.createBranch(branchDto, null));
  }


  
}
