package com.saas.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.StoreDto;
import com.saas.pos.dto.StoreStatus;
import com.saas.pos.exception.UserException;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;

@Service
public interface StoreService {

  StoreDto createStore(StoreDto storeDto, User user);
  StoreDto getStoreById(Long id) throws Exception;
  List<StoreDto> getAllStores();
  Store getStoreByAdmin() throws UserException;
  StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;
  void deleteStore(Long id) throws UserException;
  StoreDto getStoreByEmployee() throws UserException;

  StoreDto moderateStore(Long id, StoreStatus status) throws Exception;
  
}
