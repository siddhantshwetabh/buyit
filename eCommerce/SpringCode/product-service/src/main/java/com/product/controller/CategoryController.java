package com.product.controller;

import com.product.dto.CategoryDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class.
 */

@RestController
@RequestMapping("/category")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/addCategory")
    public @ResponseBody
    String addProduct(@RequestBody CategoryDTO categoryDTO) {
        logger.info("CategoryController-addCategory: Entering addCategory method");
        if (categoryDTO != null) {
            return categoryService.addCategory(categoryDTO);
        } else {
            logger.error(" Add Category - Something went wrong!! ,Category was not add successfully");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Category input data is not valid to add.");
        }
    }

    @PutMapping(value = "/updateCategory")
    public @ResponseBody
    String updateCategory(@RequestBody CategoryDTO categoryRequest) {
        logger.info("CategoryController-updateCategory: Entering updateCategory method");
        if (categoryRequest != null) {
            categoryService.updateCategory(categoryRequest);

            return "Successfully updated!!";
        } else {
            logger.error(" Update Category - Something went wrong!! ,Category was not updated successfully");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Category input data is not valid to update.");
        }
    }


    @DeleteMapping(value = "/deleteCategory/{categoryId}")
    public @ResponseBody
    String deleteCategory(@PathVariable("categoryId") Long categoryId) {
        logger.info("CategoryController-deleteCategory: Entering deleteCategory method");
        categoryService.deleteCategory(categoryId);
        return "Category Deleted successfully";
    }

    @GetMapping(value = "/getAllCategories")
    public @ResponseBody
    ResponseEntity<List<CategoryDTO>> getAllCategories() {
        logger.info("CategoryController-getAllCategories:Entering getAllCategories method");
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategory();
        if (categoryDTOList == null) {
            logger.error("CategoryController-Get Category list: Category not found");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Product not found");
        }
        return ResponseEntity.ok().body(categoryDTOList);
    }



}