package com.ecom.restController;

import com.ecom.model.Product;
import com.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/product-controller")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product-details")
    public ResponseEntity<List<Product>> getProductList(){
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }

    @GetMapping("/product-detail/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try {
            return new ResponseEntity<>(productService.addProduct(product,imageFile),HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/product-detail/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        byte[] productImageFile = product.getProductImage();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getProductImageType()))
                .body(productImageFile);
    }

    @PutMapping("product-update/{productId}")
    public ResponseEntity<String> updateProduct(@PathVariable int productId,
                                                @RequestPart Product product,
                                                @RequestPart MultipartFile imageFile){
        Product updatedProduct = productService.updateProduct(productId,product,imageFile);
        if(updatedProduct != null)
            return new ResponseEntity<>("Product updated successfully.",HttpStatus.OK);
        else
            return new ResponseEntity<>("Filed to update..",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product-delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productId){
        Product productToDelete = productService.getProductById(productId);
        if(productToDelete != null) {
            productService.deleteProduct(productId);
            return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Product not found.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(String keyword){
        List<Product> products = productService.searchProducts(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }


}
