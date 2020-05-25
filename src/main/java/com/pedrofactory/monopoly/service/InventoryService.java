package com.pedrofactory.monopoly.service;

import com.pedrofactory.monopoly.dto.InventoryRequest;
import com.pedrofactory.monopoly.dto.InventoryResponse;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> getAllInventory(Integer page, Integer size);
    InventoryResponse updateInventory(Long inventoryId, InventoryRequest inventory) throws InventoryNotFoundException;
    InventoryResponse getInventoryByUserId(Long userId) throws UserNotFoundException, InventoryNotFoundException;
}
