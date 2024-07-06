package com.ecom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_details")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         productId;
    private String      productName;
    private String      productDesc;
    private String      productBrand;
    private BigDecimal  productPrice;

   // @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-mm-yyyy")
    private LocalDate   productReleaseDate;
    private boolean     productAvailable;
    private int         productQuantity;

    private String      productImageName;
    private String      productImageType;
    @Lob
    private byte[]      productImage;
}
