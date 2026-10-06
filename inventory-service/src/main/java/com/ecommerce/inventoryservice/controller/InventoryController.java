package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.dto.ReserveBody;
import com.ecommerce.inventoryservice.dto.ReserveResponse;
import com.ecommerce.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/reserve")
    public ResponseEntity<ReserveResponse> reserve(@RequestBody ReserveBody reserveBody){
        ReserveResponse reserveResponse = inventoryService.reserve(reserveBody);
        return ResponseEntity.ok(reserveResponse);
    }

}
