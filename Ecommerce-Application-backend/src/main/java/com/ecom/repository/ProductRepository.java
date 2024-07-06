package com.ecom.repository;

import com.ecom.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where " +
    "lower(p.productName) like lower(concat('%',:keyword,'%')) or " +
    "lower(p.productDesc) like lower(concat('%',:keyword,'%')) or " +
            "lower(p.productBrand) like lower(concat('%',:keyword,'%')) ")
    List<Product> searchProducts(String keyword);
}
