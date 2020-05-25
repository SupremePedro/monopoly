package com.pedrofactory.monopoly.service.impl;

import com.pedrofactory.monopoly.dto.ItemRequest;
import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.ItemNotFoundException;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.repository.InventoryRepository;
import com.pedrofactory.monopoly.repository.ItemRepository;
import com.pedrofactory.monopoly.service.ItemService;
import com.pedrofactory.monopoly.utils.mapstruct.ItemMapper;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {
    final ItemRepository itemRepository;
    final InventoryRepository inventoryRepository;

    @Override
    public ItemResponse addItem(ItemRequest itemRequest) throws InventoryNotFoundException {
        Item newItem = ItemMapper.INSTANCE.itemRequestToItem(itemRequest);
        newItem.setInventory(inventoryRepository.findById(itemRequest.getInventoryId()).orElseThrow(()->new InventoryNotFoundException()));
        Item savedItem = itemRepository.save(newItem);
        ItemResponse itemResponse = ItemMapper.INSTANCE.itemToItemResponse(savedItem);
        return itemResponse;
    }

    @Override
    public ItemResponse getItem(Long itemId) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(()->new ItemNotFoundException(itemId.toString()));
        ItemResponse itemResponse = ItemMapper.INSTANCE.itemToItemResponse(item);
        return itemResponse;
    }

    @Override
    public List<ItemResponse> getAllItem(Integer page, Integer size) {
        List<Item> items = itemRepository.findAll(PageRequest.of(page, size)).getContent();

        List<ItemResponse> itemResponses = new ArrayList<>();
        items.forEach(item -> {
            ItemResponse itemResponse = ItemMapper.INSTANCE.itemToItemResponse(item);
            itemResponses.add(itemResponse);
        });
        return itemResponses;
    }

    @Override
    public ItemResponse updateItem(Long itemId, ItemRequest itemRequest) throws ItemNotFoundException {
        Item item = itemRepository.findById(itemId).orElseThrow(()->new ItemNotFoundException(itemId.toString()));
        Item updatedItem  = ItemMapper.INSTANCE.updateItemWithItemRequest(itemRequest,item);
        ItemResponse itemResponse = ItemMapper.INSTANCE.itemToItemResponse(itemRepository.save(updatedItem));
        return itemResponse;
    }

    @Override
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }


}
