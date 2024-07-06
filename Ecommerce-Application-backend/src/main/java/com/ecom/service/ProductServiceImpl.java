package com.ecom.service;

import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    @Override
    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setProductImageName(imageFile.getOriginalFilename());
        product.setProductImageType(imageFile.getContentType());
        product.setProductImage(imageFile.getBytes());

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(int productId, Product product, MultipartFile imageFile) {
        try {
            product.setProductImage(imageFile.getBytes());
            product.setProductImageName(imageFile.getOriginalFilename());
            product.setProductImageType(imageFile.getContentType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.searchProducts(keyword);
    }

}
