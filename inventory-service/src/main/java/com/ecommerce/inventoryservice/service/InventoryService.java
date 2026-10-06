package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.dto.ReserveBody;
import com.ecommerce.inventoryservice.dto.ReserveResponse;
import com.ecommerce.inventoryservice.entity.InventoryEntity;
import com.ecommerce.inventoryservice.exception.InsufficientStockException;
import com.ecommerce.inventoryservice.exception.InvalidQuantityException;
import com.ecommerce.inventoryservice.exception.InventoryNotFoundException;
import com.ecommerce.inventoryservice.repository.InventoryRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    @Transactional
    public ReserveResponse reserve(ReserveBody reserveBody) {

        if (reserveBody.getRequestedQuantity() == null || reserveBody.getRequestedQuantity() <= 0) {
            throw new InvalidQuantityException("Requested quantity must be greater than 0");
        }

        InventoryEntity entity = repository
                .findByProductId(reserveBody.getProductId())
                .orElseThrow(() ->
                        new InventoryNotFoundException("Inventory not found for productId: " + reserveBody.getProductId())
                );

        if (reserveBody.getRequestedQuantity() > entity.getAvailableQuantity()) {
            throw new InsufficientStockException("Insufficient stock for productId: " + reserveBody.getProductId()
            );
        }

        entity.setAvailableQuantity(entity.getAvailableQuantity() - reserveBody.getRequestedQuantity());
        entity.setReservedQuantity(entity.getReservedQuantity() + reserveBody.getRequestedQuantity());
        InventoryEntity savedEntity = repository.save(entity);

        ReserveResponse reserveResponse = new ReserveResponse();
        reserveResponse.setProductId(savedEntity.getProductId());
        reserveResponse.setAvailableQuantity(savedEntity.getAvailableQuantity());
        reserveResponse.setReservedQuantity(savedEntity.getReservedQuantity());
        reserveResponse.setStatus("RESERVED");

        return reserveResponse;
    }
}