package com.pedrofactory.monopoly.utils.mapstruct;

import com.pedrofactory.monopoly.dto.ItemRequest;
import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.repository.InventoryRepository;
import com.pedrofactory.monopoly.service.ItemService;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = {InventoryMapper.class})
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    @Mapping(source = "id",target = "itemId")
    @Mapping(source = "inventory.id", target = "inventoryId")
    ItemResponse itemToItemResponse(Item item);

    @Mapping(source = "inventoryId", target = "inventory.id")
    Item itemRequestToItem(ItemRequest itemRequest);

    Item updateItemWithItemRequest(ItemRequest itemRequest, @MappingTarget Item entity);

    default Long mapItemToString(Item item) {
        return item.getId();
    }

    default Item mapStringToItem(Long id) {
        Item newUser = new Item();
        newUser.setId(id);
        return newUser;
    }


}
