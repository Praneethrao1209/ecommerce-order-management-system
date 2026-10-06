package com.ecommerce.inventoryservice.exception;


public class InvalidQuantityException extends RuntimeException {

    public InvalidQuantityException(String errorMessage){
     super(errorMessage);
    }
}
