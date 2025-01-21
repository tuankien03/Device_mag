package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.DTO.UserDTO;
import com.bkav.device_mag_backend.entity.User;
import com.bkav.device_mag_backend.response.ApiResponse;
import com.bkav.device_mag_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, userService.findAll(), CodeStatus.SUCCESS_TEXT));
        } catch (Exception e) {
            return ResponseEntity.status(CodeStatus.NOT_FOUND).body(new ApiResponse(CodeStatus.NOT_FOUND, e.getMessage(), CodeStatus.NOT_FOUND_TEXT));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, userService.findById(id), CodeStatus.SUCCESS_TEXT));
        } catch (Exception e) {
            return ResponseEntity.status(CodeStatus.NOT_FOUND).body(new ApiResponse(CodeStatus.NOT_FOUND, e.getMessage(), CodeStatus.NOT_FOUND_TEXT));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User request) {
        try {
            User user = userService.save(request);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(new ApiResponse(CodeStatus.CREATED, userDTO, CodeStatus.CREATED_TEXT));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.BAD_REQUEST, null, e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        try {
            User user = userService.delete(id);
            UserDTO userDTO = new UserDTO(user);
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, userDTO, CodeStatus.SUCCESS_TEXT));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.BAD_REQUEST, null, e.getMessage()));
        }
    }
}
