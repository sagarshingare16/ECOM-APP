package com.ecom.service;

import com.ecom.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface ProductService {
    List<Product> getProductList();
    Product getProductById(int productId);
    Product addProduct(Product product, MultipartFile imageFile) throws IOException;
    Product updateProduct(int productId, Product product, MultipartFile imageFile);
    void deleteProduct(int productId);
    List<Product> searchProducts(String keyword);
}
