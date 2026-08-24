package com.ecommerce.productservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String sku;
    private String name;
    private String description;
    private BigDecimal price;
    private String status;
}
