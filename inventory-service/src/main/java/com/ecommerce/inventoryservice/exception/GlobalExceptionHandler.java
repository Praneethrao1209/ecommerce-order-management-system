package com.ecommerce.inventoryservice.exception;

import com.ecommerce.inventoryservice.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestedQuantity(InvalidQuantityException invalidQuantityException , HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(invalidQuantityException.getMessage());
        errorResponse.setErrorCode("INVALID_QUANTITY");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ErrorResponse>  handleInventoryNotFound(InventoryNotFoundException inventoryNotFoundException, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(inventoryNotFoundException.getMessage());
        errorResponse.setErrorCode("INVENTORY_NOT_FOUND");
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse>  handleInsufficientStock(InsufficientStockException insufficientStockException, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorMessage(insufficientStockException.getMessage());
        errorResponse.setErrorCode("INSUFFICIENT_STOCK");
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setTimestamp(Instant.now());
        errorResponse.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
}
