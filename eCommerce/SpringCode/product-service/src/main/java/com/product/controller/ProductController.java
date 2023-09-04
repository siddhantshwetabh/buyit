package com.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.dto.ProductDTO;
import com.product.exception.ResourceNotFoundException;
import com.product.model.Image;
import com.product.model.Product;
import com.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller class.
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    ProductService productService;

    @PostMapping(value = "/addProduct/{id}")
    public @ResponseBody
    String addProduct(@RequestBody ProductDTO productDTO, @PathVariable long id) {
        logger.info("ProductController-addProduct: Entering addProduct method");
        if (productDTO != null) {
            return productService.addProduct(productDTO,id);
        } else {
            logger.error(" Add Product - Something went wrong!! ,Product was not add successfully");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Product input data is not valid to add.");
        }
    }

    @PutMapping(value = "/updateProduct")
    public @ResponseBody
    String updateProduct(@RequestBody ProductDTO productRequest) {
        logger.info("ProductController-updateProduct: Entering updateProduct method");
        if (productRequest != null) {
            productService.updateProduct(productRequest);
            return "Successfully updated!!";
        } else {
            logger.error(" Update Product - Something went wrong!! ,Product was not updated successfully");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(),
                    "Product input data is not valid to update.");
        }
    }


    @DeleteMapping(value = "/deleteProduct/{ProductId}")
    public @ResponseBody
    String deleteProduct(@PathVariable("ProductId") Long productId) {
        logger.info("ProductController-deleteProduct: Entering deleteProduct method");
        productService.deleteProduct(productId);
        return  "Product Deleted syccessfully";
    }

    @GetMapping(value = "/getAllProduct")
    public @ResponseBody
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        logger.info("ProductController-getAllProducts:Entering getAllProducts method");
        List<ProductDTO> productDTOList = productService.getAllProduct();
        if (productDTOList == null) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Products not found");
        }
        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping(value = "/getAllProductsByCategory")
    public @ResponseBody
    ResponseEntity<List<ProductDTO>> getAllProductsByCategory(Long categoryId) {
        logger.info("ProductController-getAllProducts:Entering getAllProducts method");
        List<ProductDTO> productDTOList = productService.getAllProductByCategoryId(categoryId);
        if (productDTOList == null) {
            logger.error("ProductController-Get Product list: Products not found");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Product not found");
        }
        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping(value = "/getProduct/{productId}")
    public @ResponseBody
    ResponseEntity<ProductDTO> getProducts(@PathVariable long productId) {
        logger.info("ProductController-getProductsById:Entering getProductsById method");
        ProductDTO list = productService.getProductById(productId);
        if (list == null) {
            logger.error("ProductController-Get Product list: Products not found");
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Product not found!");
        }
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value= {"/addImageToProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProductWithImage(@RequestPart("product") String product,
                                          @RequestPart("categoryId") String categoryId,
                                          @RequestPart("imageFile")MultipartFile[] file){
        logger.info("ProductController-addNewProductWithImage:Entering addNewProductWithImage method");
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Product product2 = objectMapper.readValue(product, Product.class);
            Set<Image> images = uploadImage(file);
            product2.setProductImage(images);
            long id=Long.parseLong(categoryId);
            return productService.addNewProduct(product2, id);
        } catch (IOException e) {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Product can't be added!");
        }

    }

    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> images = new HashSet<>();

        for(MultipartFile file: multipartFiles){
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            images.add(image);
        }
        return images;
    }

}