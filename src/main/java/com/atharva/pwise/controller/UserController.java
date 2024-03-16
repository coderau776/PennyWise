package com.atharva.pwise.controller;

import com.atharva.pwise.entity.User;
import com.atharva.pwise.entity.payload.UserPayload;
import com.atharva.pwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserPayload userPayload) {
        return new ResponseEntity<>(userService.addUser(userPayload), HttpStatus.CREATED);
    }
    @GetMapping("/getInfo")
    public User getUser(@RequestParam String lastName, @RequestParam String phone) {
        return userService.getUserInfo(lastName, phone);
    }
    @GetMapping("/")
    public User getUserById(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }
    @DeleteMapping("/")
    public void deleteUser(@RequestParam Long userId) {
        userService.removeUser(userId);
    }


}
