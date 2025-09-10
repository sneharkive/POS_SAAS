package com.saas.pos.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.StoreDto;
import com.saas.pos.dto.StoreStatus;
import com.saas.pos.exception.UserException;
import com.saas.pos.mapper.StoreMapper;
import com.saas.pos.model.Store;
import com.saas.pos.model.StoreContact;
import com.saas.pos.model.User;
import com.saas.pos.repository.StoreRepository;
import com.saas.pos.service.StoreService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;
  private final UserService userService;

  @Override
  public StoreDto createStore(StoreDto storeDto, User user) {
    Store store = StoreMapper.toEntity(storeDto, user);

    return StoreMapper.toDTO(storeRepository.save(store));
  }

  @Override
  public StoreDto getStoreById(Long id) throws Exception {
    Store store = storeRepository.findById(id).orElseThrow(() -> new Exception("Store Not Found"));

    return StoreMapper.toDTO(store);
  }

  @Override
  public List<StoreDto> getAllStores() {
    List<Store> dtos = storeRepository.findAll();

    return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public Store getStoreByAdmin() throws UserException {
    User admin = userService.getCurrentUser();

    return storeRepository.findByStoreAdminId(admin.getId());
  }

  @Override
  public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception{
    User currUser = userService.getCurrentUser();

    Store existing = storeRepository.findByStoreAdminId(currUser.getId());

    if(existing == null ) throw new Exception("Store not founded");

    existing.setBrand(storeDto.getBrand());
    existing.setDescription(storeDto.getDescription());

    if(storeDto.getStoreType() != null) existing.setStoreType(storeDto.getStoreType());
    if(storeDto.getContact() != null) {
      StoreContact contact = StoreContact.builder()
                              .address(storeDto.getContact().getAddress())
                              .phone(storeDto.getContact().getPhone())
                              .email(storeDto.getContact().getEmail())
                              .build();
      existing.setContact(contact);
    }
    return StoreMapper.toDTO(storeRepository.save(existing));

  }

  @Override
  public void deleteStore(Long id) throws UserException {
    Store store = getStoreByAdmin();
    storeRepository.delete(store);
  }

  @Override
  public StoreDto getStoreByEmployee() throws UserException {
    User currUser = userService.getCurrentUser();

    if(currUser == null)
      throw new UserException("You don't have permission to access this store.");

    return StoreMapper.toDTO(currUser.getStore());
  }

  @Override
  public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
    Store store = storeRepository.findById(id).orElseThrow(() -> new Exception("Store not found with id: " + id));

    store.setStatus(status);
    return StoreMapper.toDTO(storeRepository.save(store));
  }
  
}
