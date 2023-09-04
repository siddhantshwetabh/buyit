package com.product.service;

import com.product.dto.ProductDTO;
import com.product.model.Product;

import java.util.List;

public interface ProductService {


    Long updateProduct(ProductDTO userRequest);

    void deleteProduct(Long userId);

    //Product addNewProduct(Product product);

    String addProduct(ProductDTO userDTO,long id);

    List<ProductDTO> getAllProduct();

    List<ProductDTO> getAllProductByCategoryId(Long categoryId);

    ProductDTO getProductById(Long productId);

    Product addNewProduct(Product product2, long id);
}