package com.saas.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.StoreDto;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;

@Service
public interface StoreService {

  StoreDto createStore(StoreDto storeDto, User user);
  StoreDto getStoreById(StoreDto storeDto, User user);
  List<StoreDto> getAllStores();
  Store getStoreByAdmin();
  StoreDto updateStore(Long id, StoreDto storeDto);
  StoreDto deleteStore(Long id);
  StoreDto getStoreByEmployee();
  
}
