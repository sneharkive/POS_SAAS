package com.saas.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saas.pos.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
  
  Store findByStoreAdminId(Long adminId);
}
