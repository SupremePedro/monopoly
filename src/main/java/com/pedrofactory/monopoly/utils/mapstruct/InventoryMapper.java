package com.pedrofactory.monopoly.utils.mapstruct;

import com.pedrofactory.monopoly.dto.InventoryRequest;
import com.pedrofactory.monopoly.dto.InventoryResponse;
import com.pedrofactory.monopoly.dto.ItemResponse;
import com.pedrofactory.monopoly.dto.UserRequest;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    Inventory inventoryRequestToInventory(InventoryRequest inventoryRequest);

    @Mapping(source = "owner.id", target = "ownerId")
    InventoryResponse inventoryToInventoryResponse(Inventory inventory);

    Inventory updateInventoryWithInventoryRequest(InventoryRequest inventoryRequest, @MappingTarget Inventory inventory);

    //User updateUserWithUserRequest(UserRequest userRequest, @MappingTarget User entity);

    @Mapping(source = "id",target = "itemId")
    @Mapping(source = "inventory.id", target = "inventoryId")
    ItemResponse itemToItemResponse(Item item);

    default Long mapItemToString(Item item) {
        return item.getId();
    }

    default Item mapStringToItem(Long id) {
        Item newUser = new Item();
        newUser.setId(id);
        return newUser;
    }

}
