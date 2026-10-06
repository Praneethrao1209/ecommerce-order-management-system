package com.ecommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveResponse {

    private Long productId;
    private Integer availableQuantity;
    private Integer reservedQuantity;
    private String status;
}
