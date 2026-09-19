package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.entity.ProductEntity;
import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductReadService {

    @Autowired
    private  ProductRepository repository;

    public ProductResponse getProductById(Long id) {
        ProductEntity product = repository.findById(id)
                .orElseThrow(()->
                        new ProductNotFoundException("Product not found with id : " + id)
                );

        return mapToResponse(product);
    }

    public ProductResponse getProductBySku(String sku){
        ProductEntity product = repository.findBySku(sku)
                .orElseThrow(() ->
                new ProductNotFoundException("Product not found with sku: "  + sku)
                );
        return mapToResponse(product);
    }

    public Page<ProductResponse> getAllProducts(int page ,int size, String sortBy , String direction){
       Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
       Pageable pageable = PageRequest.of(page, size, sort);
       Page<ProductEntity> productPage = repository.findAll(pageable);
       return productPage.map(this::mapToResponse);
    }

    private ProductResponse mapToResponse(ProductEntity product){
        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}


