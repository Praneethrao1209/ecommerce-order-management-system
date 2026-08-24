package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductRequest;
import com.ecommerce.productservice.dto.ProductResponse;
import com.ecommerce.productservice.entity.ProductEntity;
import com.ecommerce.productservice.exception.ProductNotFoundException;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductWriteService {

    @Autowired
    private ProductRepository repository;

    public ProductResponse createProduct(ProductRequest request) {

        ProductEntity entity = new ProductEntity();

        entity.setSku(request.getSku());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setPrice(request.getPrice());
        entity.setStatus(request.getStatus());

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        ProductEntity savedProduct = repository.save(entity);

        return mapToResponse(savedProduct);
    }

    public ProductResponse updateProductById(Long id , ProductRequest request){
        ProductEntity product = repository.findById(id)
                .orElseThrow(() ->
                    new ProductNotFoundException("Product not found for id:"+ id)
                );
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStatus(request.getStatus());
        ProductEntity updatedProduct = repository.save(product);
        return mapToResponse(updatedProduct);
    }

    public ProductResponse updateProductByStatus(Long id , ProductRequest request){
        ProductEntity product = repository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found for id:"+ id)
                );
        product.setStatus(request.getStatus());
        product.setUpdatedAt(LocalDateTime.now());
        ProductEntity updatedProduct = repository.save(product);
        return mapToResponse(updatedProduct);
    }

    private ProductResponse mapToResponse(ProductEntity product) {

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