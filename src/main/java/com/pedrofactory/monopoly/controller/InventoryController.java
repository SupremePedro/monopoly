package com.pedrofactory.monopoly.controller;

import com.pedrofactory.monopoly.dto.InventoryRequest;
import com.pedrofactory.monopoly.dto.InventoryResponse;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@AllArgsConstructor
public class InventoryController {

    final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>>getAllInventory(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size){
        return new ResponseEntity<>(inventoryService.getAllInventory(page,size), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<InventoryResponse>getInventory(@PathVariable Long userId) throws UserNotFoundException, InventoryNotFoundException {
        return new ResponseEntity<>(inventoryService.getInventoryByUserId(userId), HttpStatus.OK);
    }
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponse> updateInventory(@RequestBody InventoryRequest inventory, @PathVariable Long inventoryId) throws InventoryNotFoundException {
        return new ResponseEntity<>(inventoryService.updateInventory(inventoryId, inventory),HttpStatus.OK);
    }
}
