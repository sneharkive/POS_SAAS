package com.saas.pos.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.dto.ProductDto;
import com.saas.pos.mapper.ProductMapper;
import com.saas.pos.model.Category;
import com.saas.pos.model.Product;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;
import com.saas.pos.repository.CategoryRepository;
import com.saas.pos.repository.ProductRepository;
import com.saas.pos.repository.StoreRepository;
import com.saas.pos.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final StoreRepository storeRepository;
  private final CategoryRepository categoryRepository;

  @Override
  public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
    Store store = storeRepository.findById(productDto.getStoreId()).orElseThrow(() -> new Exception("Store Not Found"));

    Category category = categoryRepository.findById(productDto.getCategoryId())
        .orElseThrow(() -> new Exception("Category not found"));
    Product product = ProductMapper.toEntity(productDto, store, category);
    return ProductMapper.toDTO(productRepository.save(product));
  }

  @Override
  public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
    Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product Not Found"));

    product.setName(productDto.getName());
    product.setDescription(productDto.getDescription());
    product.setSku(productDto.getSku());
    product.setImage(productDto.getImage());
    product.setMrp(productDto.getMrp());
    product.setSellingPrice(productDto.getSellingPrice());
    product.setBrand(productDto.getBrand());
    product.setUpdatedAt(LocalDateTime.now());

    if (productDto.getCategoryId() != null) {
      Category category = categoryRepository.findById(productDto.getCategoryId())
          .orElseThrow(() -> new Exception("Category not found"));
      if (category != null)
        product.setCategory(category);
    }

    return ProductMapper.toDTO(productRepository.save(product));
  }

  @Override
  public void deleteProduct(Long id, User user) throws Exception {
    Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product Not Found"));

    productRepository.delete(product);
  }

  @Override
  public List<ProductDto> getProductByStoreId(Long storeId) throws Exception {
    List<Product> products = productRepository.findByStoreId(storeId);

    return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public List<ProductDto> searchByKeyword(Long storeId, String keyword) throws Exception {
    List<Product> products = productRepository.searchByKeyword(storeId, keyword);

    return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
  }

}
