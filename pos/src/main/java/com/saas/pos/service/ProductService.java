package com.saas.pos.service;

import java.util.List;

import com.saas.pos.dto.ProductDto;
import com.saas.pos.model.User;

public interface ProductService {
  
  ProductDto createProduct(ProductDto productDto, User user) throws Exception;
  ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;
  void deleteProduct(Long id, User user) throws Exception;
  List<ProductDto> getProductByStoreId(Long storeId) throws Exception;
  List<ProductDto> searchByKeyword(Long storeId, String keyword) throws Exception;

}
