package com.pedrofactory.monopoly.service;

import com.pedrofactory.monopoly.dto.*;
import com.pedrofactory.monopoly.exception.UserAlreadyExistsException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUser(Integer page, Integer size);
    UserResponse addUser(UserRegisterRequest user) throws UserNotFoundException, UserAlreadyExistsException;
    UserResponse getUser(Long id) throws UserNotFoundException;
    UserResponse updateUser(Long id, UserRequest user) throws UserNotFoundException;
    void deleteUser(Long userId);
    List<FriendResponse> getAllFriends(Long userId);
    IsFriend isFriend(Long userId, Long friendId);
    IsFriend updateFriendship(Long userId,Long friendId,IsFriend isFriend);

}
