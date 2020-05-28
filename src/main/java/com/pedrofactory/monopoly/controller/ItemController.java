package com.pedrofactory.monopoly.controller;

import com.pedrofactory.monopoly.dto.ItemRequest;
import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.exception.InventoryNotFoundException;
import com.pedrofactory.monopoly.exception.ItemNotFoundException;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    final private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> addItem(@Valid @RequestBody ItemRequest item) throws InventoryNotFoundException {
        return new ResponseEntity<>(itemService.addItem(item),HttpStatus.CREATED);
    }


    @GetMapping(value = "/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.getItem(itemId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ItemResponse>> getAllItem(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size){
        return new ResponseEntity<>(itemService.getAllItem(page, size),HttpStatus.OK);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long itemId, @Valid @RequestBody ItemRequest item) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.updateItem(itemId, item),HttpStatus.OK);
    }

    @DeleteMapping(value = "/{itemId}")
    public ResponseEntity<Void> delete(@PathVariable Long itemId){
        itemService.deleteItem(itemId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
