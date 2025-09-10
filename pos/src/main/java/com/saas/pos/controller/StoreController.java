package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.StoreDto;
import com.saas.pos.exception.UserException;
import com.saas.pos.mapper.StoreMapper;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;
import com.saas.pos.service.StoreService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;
  private final UserService userService;

  @PostMapping()
  public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto, @RequestHeader("Authorization") String jwt) throws UserException {
    User user = userService.getUserFromJwtToken(jwt);
    return ResponseEntity.ok(storeService.createStore(storeDto, user));
  }

  @GetMapping("/{id}")
  public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(storeService.getStoreById(id));
  }

  @GetMapping()
  public ResponseEntity<List<StoreDto>> getAllStore(@RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(storeService.getAllStores());
  }
  

  @GetMapping("/admin")
  public ResponseEntity<StoreDto> getStoreByAdmin(@RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
  }

  @GetMapping("/employee")
  public ResponseEntity<StoreDto> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(storeService.getStoreByEmployee());
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) throws Exception {
      
      return ResponseEntity.ok(storeService.updateStore(id, storeDto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id) throws Exception {
    storeService.deleteStore(id);
    
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setMessage("Store Deleted Successfully");

    return ResponseEntity.ok(apiResponse);
  }
}
