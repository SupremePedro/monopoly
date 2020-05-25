package com.pedrofactory.monopoly.service.impl;

import com.pedrofactory.monopoly.dto.*;
import com.pedrofactory.monopoly.entity.Inventory;
import com.pedrofactory.monopoly.entity.Role;
import com.pedrofactory.monopoly.entity.User;
import com.pedrofactory.monopoly.exception.UserAlreadyExistsException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.repository.InventoryRepository;
import com.pedrofactory.monopoly.repository.RoleRepository;
import com.pedrofactory.monopoly.repository.UserRepository;
import com.pedrofactory.monopoly.service.UserService;
import com.pedrofactory.monopoly.utils.mapstruct.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final RoleRepository roleRepository;
    final InventoryRepository inventoryRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse addUser(UserRegisterRequest user)  throws UserAlreadyExistsException {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new UserAlreadyExistsException(user.getEmail());
        }
        User newUser = UserMapper.INSTANCE.userRequestToUser(user);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        Role newRole = new Role();
        newRole.setName("ROLE_USER");
        newRole.setUsers(new ArrayList<>());
        Role savedRole = roleRepository.save(newRole);
        List<Role> roles = Arrays.asList(savedRole);
        newUser.setRoles(roles);
        Inventory newInventory = inventoryRepository.save(new Inventory());
        newInventory.setOwner(newUser);
        User savedUser = userRepository.save(newUser);
        savedRole.getUsers().add(savedUser);
        roleRepository.save(savedRole);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(savedUser);
        return userResponse;
    }

    @Override
    public UserResponse getUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id.toString()));
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(user);
        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        User updateUser = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id.toString()));
        updateUser = UserMapper.INSTANCE.updateUserWithUserRequest(userRequest,updateUser);
        UserResponse userResponse = UserMapper.INSTANCE.userToUserResponse(userRepository.save(updateUser));
        return userResponse;
    }


    @Override
    public List<UserResponse> getAllUser(Integer page, Integer size) {
        List<User> users= userRepository.findAll(PageRequest.of(page, size)).getContent();
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user:users
             ) {
            userResponses.add(UserMapper.INSTANCE.userToUserResponse(user));
        }
        return userResponses;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<FriendResponse> getAllFriends(Long userId) {
        User user = userRepository.getOne(userId);
        List<FriendResponse> friendResponses = new ArrayList<>();
        user.getFriends().forEach(friend->{
            FriendResponse friendResponse = UserMapper.INSTANCE.userToFriendResponse(friend);
            friendResponses.add(friendResponse);
        });

        return friendResponses;
    }

    @Override
    public IsFriend isFriend(Long userId, Long friendId) {
        User user = userRepository.getOne(userId);
        User friend = userRepository.getOne(friendId);
        if(user.getFriends().contains(friend)){
            return new IsFriend(true);
        }else{
            return new IsFriend(false);
        }
    }

    @Override
    public IsFriend updateFriendship(Long userId, Long friendId, IsFriend isFriend) {
        User user = userRepository.getOne(userId);
        User friend = userRepository.getOne(friendId);
        if(isFriend.getIsFriend()){
            if(!user.getFriends().contains(friend)){
                user.getFriends().add(friend);
                userRepository.save(user);
            }
        }else{
            if(user.getFriends().contains(friend)){
                user.getFriends().remove(friend);
                userRepository.save(user);
            }
        }
        return isFriend(userId,friendId);
    }
}
