package com.product.serviceimpl;

import com.product.dao.CategoryDAO;
import com.product.dto.CategoryDTO;
import com.product.exception.ResourceServerException;
import com.product.model.Category;
import com.product.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    CategoryDAO categoryDao;

    @Override
    public Long updateCategory(CategoryDTO categoryRequest) {
        logger.info("CategoryServiceImpl- updateCategory: Entering updateCategory method");
        Category oldCategory = categoryDao.findById(categoryRequest.getCategoryId()).orElse(null);
        if (oldCategory == null) {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category doesn't exists. Please add Category");
        }
        if (categoryRequest.getCategoryType() != null) {
            oldCategory.setCategoryType(categoryRequest.getCategoryType());
        }
        categoryDao.save(oldCategory);
        return oldCategory.getCategoryId();
    }

    @Override
    public void deleteCategory(Long userId) {
        logger.info("CategoryServiceImpl- deleteCategory: Entering deleteCategory method");
        Category oldCategory = categoryDao.getById(userId);
            categoryDao.delete(oldCategory);
        }

    @Override
    public String addCategory(CategoryDTO categoryDTO) {
        logger.info("CategoryServiceImpl- addCategory: Entering addCategory method");
        Category newCategory = new Category();
        newCategory.setCategoryType(categoryDTO.getCategoryType());
        categoryDao.save(newCategory);
        return "Category added successfully";
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        logger.info("CategoryServiceImpl- getAllCategory: Entering getAllCategory method");
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        List<Category> categories = categoryDao.findAll();
        if (!categories.isEmpty()) {
            categories.forEach(category -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setCategoryId(category.getCategoryId());
                categoryDTO.setCategoryType(category.getCategoryType());
                categoryDTOList.add(categoryDTO);
            });
        }
        return categoryDTOList;
    }
}