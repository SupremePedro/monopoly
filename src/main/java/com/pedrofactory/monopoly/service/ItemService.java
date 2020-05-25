package com.pedrofactory.monopoly.service;

import com.pedrofactory.monopoly.dto.ItemRequest;
import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.ItemNotFoundException;
import com.pedrofactory.monopoly.entity.Item;


import java.util.List;

public interface ItemService {

    ItemResponse addItem(ItemRequest item) throws InventoryNotFoundException;

    ItemResponse getItem(Long itemId) throws ItemNotFoundException;
    List<ItemResponse> getAllItem(Integer page, Integer size);
    ItemResponse updateItem(Long itemId, ItemRequest itemRequest) throws ItemNotFoundException;
    void deleteItem(Long itemId);

}
