package com.saas.pos.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saas.pos.domain.UserRole;
import com.saas.pos.dto.CategoryDto;
import com.saas.pos.mapper.CategoryMapper;
import com.saas.pos.model.Store;
import com.saas.pos.model.User;
import com.saas.pos.model.Category;
import com.saas.pos.repository.CategoryRepository;
import com.saas.pos.repository.StoreRepository;
import com.saas.pos.service.CategoryService;
import com.saas.pos.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserService userService;
  private final StoreRepository storeRepository;

  @Override
  public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
    User user = userService.getCurrentUser();

    Store store = storeRepository.findById(categoryDto.getStoreId()).orElseThrow(() -> new Exception("Store Not Found"));

    Category category = Category.builder()
                        .store(store)
                        .name(categoryDto.getName())
                        .build();

    checkAuthority(user, category.getStore());

    return CategoryMapper.toDTO(categoryRepository.save(category));
  }

  @Override
  public List<CategoryDto> getCategoriesByStore(Long storeId) throws Exception {
    List<Category> categories = categoryRepository.findByStoreId(storeId);
    return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
  }

  @Override
  public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

    User user = userService.getCurrentUser();
    checkAuthority(user, category.getStore());

    category.setName(categoryDto.getName());
    return CategoryMapper.toDTO(categoryRepository.save(category));

  }

  @Override
  public void deleteCategory(Long id) throws Exception {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

    User user = userService.getCurrentUser();

    checkAuthority(user, category.getStore());

    categoryRepository.delete(category);
  }

  private void checkAuthority(User user, Store store) throws Exception{
    boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
    boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
    boolean isSameStore = user.equals(store.getStoreAdmin());

    if(!(isAdmin && isSameStore) && !isManager) throw new Exception("You don't have permission to manage this category");
  }

  
  
}
