package com.pedrofactory.monopoly.service.impl;

import com.pedrofactory.monopoly.dto.InventoryRequest;
import com.pedrofactory.monopoly.dto.InventoryResponse;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.User;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.repository.InventoryRepository;
import com.pedrofactory.monopoly.repository.UserRepository;
import com.pedrofactory.monopoly.service.InventoryService;
import com.pedrofactory.monopoly.service.UserService;
import com.pedrofactory.monopoly.utils.mapstruct.InventoryMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    final InventoryRepository inventoryRepository;
    final UserService userService;
    final UserRepository userRepository;


    @Override
    public List<InventoryResponse> getAllInventory(Integer page, Integer size) {
        List<Inventory> inventories = inventoryRepository.findAll(PageRequest.of(page, size)).getContent();
        List<InventoryResponse> inventoryResponses = new ArrayList<>();
        inventories.forEach(inventory -> {
            InventoryResponse inventoryResponse = InventoryMapper.INSTANCE.inventoryToInventoryResponse(inventory);
            inventoryResponses.add(inventoryResponse);
        });
        return inventoryResponses;
    }



    @Override
    public InventoryResponse updateInventory(Long inventoryId, InventoryRequest inventory) throws InventoryNotFoundException {
        Inventory updateInventory = inventoryRepository.findById(inventoryId).orElseThrow(()->new InventoryNotFoundException());
        updateInventory = InventoryMapper.INSTANCE.updateInventoryWithInventoryRequest(inventory, updateInventory);
        InventoryResponse inventoryResponse = InventoryMapper.INSTANCE.inventoryToInventoryResponse(inventoryRepository.save(updateInventory));
        return inventoryResponse;
    }

    @Override
    public InventoryResponse getInventoryByUserId(Long userId) throws UserNotFoundException, InventoryNotFoundException {
        User user  = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId.toString()));
        Inventory inventory =  inventoryRepository.findById(user.getInventory().getId()).orElseThrow(()-> new InventoryNotFoundException());
        InventoryResponse inventoryResponse  = InventoryMapper.INSTANCE.inventoryToInventoryResponse(inventory);
        return inventoryResponse;
    }

}
