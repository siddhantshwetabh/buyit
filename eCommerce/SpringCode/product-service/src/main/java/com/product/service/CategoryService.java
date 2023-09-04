package com.product.service;

import com.product.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {


    Long updateCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);

    String addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategory();
}