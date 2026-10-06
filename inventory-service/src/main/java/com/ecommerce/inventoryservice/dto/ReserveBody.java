package com.ecommerce.inventoryservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveBody {

    private Long productId;
    private Integer requestedQuantity;
}
