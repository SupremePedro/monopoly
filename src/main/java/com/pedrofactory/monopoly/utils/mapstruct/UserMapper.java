package com.pedrofactory.monopoly.utils.mapstruct;

import com.pedrofactory.monopoly.dto.*;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.Item;
import com.pedrofactory.monopoly.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse userToUserResponse(User user);

    InventoryResponse inventoryToInventoryResponse(Inventory inventory);

    ItemResponse itemToItemResponse(Item item);

    User userRequestToUser(UserRegisterRequest userRegisterRequest);

    User updateUserWithUserRequest(UserRequest userRequest, @MappingTarget User entity);

    default FriendResponse userToFriendResponse(User user){
        FriendResponse response = new FriendResponse();
        response.setFriendId(user.getId());
        response.setNickname(user.getNickname());
        return response;
    }
    default Long mapUserToString(User user) {
        return user.getId();
    }

    default User mapStringToUser(Long id) {
        User newUser = new User();
        newUser.setId(id);
        return newUser;
    }
}
