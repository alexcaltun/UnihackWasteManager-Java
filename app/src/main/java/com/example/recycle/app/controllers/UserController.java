package com.example.recycle.app.controllers;

import com.example.recycle.app.models.User;
import com.example.recycle.app.models.dto.PlainUserDto;
import com.example.recycle.app.models.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.recycle.app.services.UserService;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<PlainUserDto> addUser (@RequestBody final PlainUserDto plainUserDto){
        User user = userService.addUser(User.from(plainUserDto));
        return new ResponseEntity<>(PlainUserDto.from(user), HttpStatus.OK);
    }

    @PostMapping("addWasteToUser/{userId}/{wasteId}")
    public ResponseEntity<UserDto> addWasteToUser(@PathVariable final Long userId, @PathVariable final Long wasteId){
        User user = userService.addWasteToUser(userId, wasteId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }

    @PostMapping("/addWasteQuantityToUser/{userId}/{type}/{quantity}")
    public ResponseEntity<UserDto> addWasteQuantityToUser (@PathVariable final Long userId, @PathVariable final String type, @PathVariable final Long quantity){
        User user = userService.addWasteQuantityToUser(userId,type,quantity);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> usersList = userService.getUsers();
        List<UserDto> usersListDto = usersList.stream().map(UserDto::from).toList();
        return new ResponseEntity<>(usersListDto, HttpStatus.OK);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable final Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    @PutMapping("editUser/{id}")
    public ResponseEntity<UserDto> editUser(@PathVariable final Long id, @RequestBody PlainUserDto plainUserDto){
        User user = userService.editUser(id, User.from(plainUserDto));
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<PlainUserDto> deleteUser(@PathVariable final Long id){
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(PlainUserDto.from(user), HttpStatus.OK);
    }

    @DeleteMapping("deleteWasteFromUser/{userId}/{wasteId}")
    public ResponseEntity<UserDto> deleteWasteFromUser(@PathVariable final Long userId, @PathVariable final Long wasteId){
        User user = userService.deleteWasteFromUser(userId, wasteId);
        return new ResponseEntity<>(UserDto.from(user), HttpStatus.OK);
    }


}
