package com.pedrofactory.monopoly.controller;

import com.pedrofactory.monopoly.dto.*;
import com.pedrofactory.monopoly.entity.User;
import com.pedrofactory.monopoly.exception.UserAlreadyExistsException;
import com.pedrofactory.monopoly.exception.UserNotFoundException;
import com.pedrofactory.monopoly.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    final private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody UserRegisterRequest user) throws UserNotFoundException, UserAlreadyExistsException {
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer size) {
        return new ResponseEntity<>(userService.getAllUser(page, size),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@RequestBody UserRequest user, @PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity(userService.updateUser(userId,user),HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping ("/{userId}/friends")
    public ResponseEntity<List<FriendResponse>> getAllFriendByUserId(@PathVariable Long userId){
        return  new ResponseEntity<>(userService.getAllFriends(userId),HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/isfriend/{friendId}")
    public ResponseEntity<IsFriend> addNewFriend(@PathVariable Long userId, @PathVariable Long friendId){
        return  new ResponseEntity<>(userService.isFriend(userId,friendId),HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}/isfriend/{friendId}")
    public ResponseEntity<IsFriend> updateFriendship(@RequestBody IsFriend isFriend, @PathVariable Long friendId, @PathVariable Long userId){
        return new ResponseEntity<>(userService.updateFriendship(userId,friendId,isFriend),HttpStatus.OK);
    }

}
