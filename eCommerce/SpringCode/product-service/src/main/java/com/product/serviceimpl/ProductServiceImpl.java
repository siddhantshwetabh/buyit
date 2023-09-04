package com.product.serviceimpl;

import com.product.dao.CategoryDAO;
import com.product.dao.ProductDAO;
import com.product.dto.ProductDTO;
import com.product.exception.ResourceServerException;
import com.product.model.Category;
import com.product.model.Product;
import com.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductDAO productDao;

    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public Long updateProduct(ProductDTO productRequest) {
        logger.info("ProductServiceImpl- updateProduct: Entering updateProduct method");
        Product oldProduct = productDao.findById(productRequest.getProductId()).orElse(null);
        if (oldProduct == null) {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Product doesn't exists. Please add Product");
        }
        if (productRequest.getProductId() != null) {
            oldProduct.setProductId(productRequest.getProductId());
        }
        if (productRequest.getProductName() != null) {
            oldProduct.setProductName(productRequest.getProductName());
        }
        if (productRequest.getProductType() != null) {
            oldProduct.setProductType(productRequest.getProductType());
        }
        if (productRequest.getProductActualPrice() != null) {
            oldProduct.setProductActualPrice(productRequest.getProductActualPrice());
        }
        if (productRequest.getProductDiscountedPrice() != null) {
            oldProduct.setProductDiscountedPrice(productRequest.getProductDiscountedPrice());
        }
        if (productRequest.getProductDescription() != null) {
            oldProduct.setProductDescription(productRequest.getProductDescription());
        }
        productDao.save(oldProduct);
        return oldProduct.getProductId();
    }

    @Override
    public void deleteProduct(Long userId) {
        logger.info("ProductServiceImpl- deleteProduct: Entering deleteProduct method");
        Product oldProduct = productDao.getById(userId);
        productDao.delete(oldProduct);
    }

    @Override
    public String addProduct(ProductDTO productDTO, long id) {
        logger.info("ProductServiceImpl- addProduct: Entering addProduct method");
        Category category = categoryDAO.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category doesn't exists. Please add Category");
        }
        Product newProduct = new Product();
        newProduct.setProductName(productDTO.getProductName());
        newProduct.setProductType(productDTO.getProductType());
        newProduct.setProductDescription(productDTO.getProductDescription());
        newProduct.setProductActualPrice(productDTO.getProductActualPrice());
        newProduct.setProductDiscountedPrice(productDTO.getProductDiscountedPrice());
        newProduct.setCategory(category);

        productDao.save(newProduct);
        return "Product added successfully";
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        logger.info("ProductServiceImpl- getAllProduct: Entering getAllProduct method");
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> product = productDao.findAll();

        if (!product.isEmpty()) {
            product.forEach(product1 -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(product1.getProductId());
                productDTO.setProductName(product1.getProductName());
                productDTO.setProductType(product1.getProductType());
                productDTO.setProductDescription(product1.getProductDescription());
                productDTO.setProductActualPrice(product1.getProductActualPrice());
                productDTO.setProductDiscountedPrice(product1.getProductDiscountedPrice());
                productDTO.setProductImage(product1.getProductImage());
                productDTO.setCategory(product1.getCategory());
                productDTOList.add(productDTO);
            });

        }

        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllProductByCategoryId(Long categoryId) {
        logger.info("ProductServiceImpl- getAllProductByCategoryId: Entering getAllProductByCategoryId method");
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> product = productDao.getProductByCategoryId(categoryId);
        if (!product.isEmpty()) {
            product.forEach(product1 -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(product1.getProductId());
                productDTO.setProductName(product1.getProductName());
                productDTO.setProductType(product1.getProductType());
                productDTO.setProductDescription(product1.getProductDescription());
                productDTO.setProductActualPrice(product1.getProductActualPrice());
                productDTO.setProductDiscountedPrice(product1.getProductDiscountedPrice());
                productDTO.setProductImage(product1.getProductImage());
                productDTO.setCategory(product1.getCategory());
                productDTOList.add(productDTO);
            });

        }
        return productDTOList;
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        logger.info("ProductServiceImpl- getProductById: Entering getProductById method");
        Product product = productDao.findById(productId).orElse(null);
        ProductDTO productDTO = new ProductDTO();
        if(product != null)
        {
            productDTO.setProductId(product.getProductId());
            productDTO.setProductName(product.getProductName());
            productDTO.setProductType(product.getProductType());
            productDTO.setProductDescription(product.getProductDescription());
            productDTO.setProductActualPrice(product.getProductActualPrice());
            productDTO.setProductDiscountedPrice(product.getProductDiscountedPrice());
            productDTO.setProductImage(product.getProductImage());
            productDTO.setCategory(product.getCategory());
            return productDTO;
        }
        else{
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Product doesn't exist");
        }
    }

    /*public Product addNewP(ProductDTO productDTO, long id) {
        logger.info("ProductServiceImpl- updateProduct: Entering updateProduct method");
        Category category=categoryDAO.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category doesn't exists. Please add Category");
        }
        Product product = new Product();
        product.setCategory(category);
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductName(productDTO.getProductName());
        product.setProductType(productDTO.getProductType());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductActualPrice(productDTO.getProductActualPrice());
        product.setProductDiscountedPrice(productDTO.getProductDiscountedPrice());
        product.setProductImage(productDTO.getProductImage());
        return productDao.save(product);

    }*/

    @Override
    public Product addNewProduct(Product product2, long id) {
        logger.info("ProductServiceImpl- addNewProduct: Entering addNewProduct method");
        Category category=categoryDAO.findById(id).orElse(null);
        if (category == null) {
            throw new ResourceServerException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Category doesn't exists. Please add Category");
        }
        product2.setCategory(category);

        return productDao.save(product2);
    }
}

