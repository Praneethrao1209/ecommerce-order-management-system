package com.ecommerce.inventoryservice.exception;

public class InsufficientStockException extends RuntimeException{

    public InsufficientStockException(String errorMessage){
        super(errorMessage);
    }
}
