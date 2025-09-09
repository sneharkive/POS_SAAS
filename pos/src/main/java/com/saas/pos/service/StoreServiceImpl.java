package com.saas.pos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.StoreDto;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;

@Service
public class StoreServiceImpl implements StoreService {

  @Override
  public StoreDto createStore(StoreDto storeDto, User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createStore'");
  }

  @Override
  public StoreDto getStoreById(StoreDto storeDto, User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStoreById'");
  }

  @Override
  public List<StoreDto> getAllStores() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllStores'");
  }

  @Override
  public Store getStoreByAdmin() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStoreByAdmin'");
  }

  @Override
  public StoreDto updateStore(Long id, StoreDto storeDto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateStore'");
  }

  @Override
  public StoreDto deleteStore(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteStore'");
  }

  @Override
  public StoreDto getStoreByEmployee() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getStoreByEmployee'");
  }
  
}
