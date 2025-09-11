package com.saas.pos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.saas.pos.dto.ApiResponse;
import com.saas.pos.dto.ProductDto;
import com.saas.pos.model.User;
import com.saas.pos.service.ProductService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final UserService userService;

  @PostMapping
  public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @RequestHeader("Authorization") String jwt) throws Exception {

    User user = userService.getUserFromJwtToken(jwt);
    return ResponseEntity.ok(productService.createProduct(productDto, user));
  }

  @GetMapping("/store/{storeId}")
  public ResponseEntity<List<ProductDto>> getProductByStoreId(@PathVariable Long storeId, @RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(productService.getProductByStoreId(storeId));
  }

  
  @PatchMapping("/{id}")
  public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto, @RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.getUserFromJwtToken(jwt);

    return ResponseEntity.ok(productService.updateProduct(id, productDto, user));
  }

  
  @GetMapping("/store/{storeId}/search")
  public ResponseEntity<List<ProductDto>> searchByKeyword(@PathVariable Long storeId, @RequestParam String keyword, @RequestHeader("Authorization") String jwt) throws Exception {

    return ResponseEntity.ok(productService.searchByKeyword(storeId, keyword));
  }

  
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.getUserFromJwtToken(jwt);
    productService.deleteProduct(id, user);

    ApiResponse apiRes = new ApiResponse();
    apiRes.setMessage("Product Deleted Successfully");

    return ResponseEntity.ok(apiRes);
  }


  
}
