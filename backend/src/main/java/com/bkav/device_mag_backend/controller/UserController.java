package com.bkav.device_mag_backend.controller;

import com.bkav.device_mag_backend.DTO.UserDTO;
import com.bkav.device_mag_backend.entity.User;
import com.bkav.device_mag_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        return ResponseEntity.status(200).body(userService.findAll());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody User user) {
        String password = user.getPassword();
        return userService.save(user);
    }


}
