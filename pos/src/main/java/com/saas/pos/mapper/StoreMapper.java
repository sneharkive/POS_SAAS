package com.saas.pos.mapper;

import com.saas.pos.dto.StoreDto;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;

public class StoreMapper {

  public static StoreDto toDTO(Store store){
    if (store == null) 
      return null; // or throw an exception if a store should always exist
    
    StoreDto storeDto = new StoreDto();
    storeDto.setId(store.getId());
    storeDto.setBrand(store.getBrand());
    storeDto.setContact(store.getContact());
    storeDto.setDescription(store.getDescription());
    storeDto.setStoreType(store.getStoreType());
    storeDto.setCreatedAt(store.getCreatedAt());
    storeDto.setUpdatedAt(store.getUpdatedAt());
    storeDto.setStoreAdmin(UserMapper.toDTO(store.getStoreAdmin()));
    storeDto.setStatus(store.getStatus());

    return storeDto;
  }

  public static Store toEntity(StoreDto storeDto, User storeAdmin){
    Store store = new Store();

    store.setId(storeDto.getId());
    store.setBrand(storeDto.getBrand());
    store.setContact(storeDto.getContact());
    store.setDescription(storeDto.getDescription());
    store.setStoreType(storeDto.getStoreType());
    store.setCreatedAt(storeDto.getCreatedAt());
    store.setUpdatedAt(storeDto.getUpdatedAt());
    store.setStatus(storeDto.getStatus());
    store.setStoreAdmin(storeAdmin);

    return store;
  }
  
}
