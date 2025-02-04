package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.service.UserService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findAll()));
        } catch (Exception e) {
            return ResponseEntity.status(CodeStatus.NOT_FOUND).body(new ApiResponse(CodeStatus.NOT_FOUND, e.getMessage(), CodeStatus.NOT_FOUND_TEXT));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(CodeStatus.NOT_FOUND).body(new ApiResponse(CodeStatus.NOT_FOUND, e.getMessage(), CodeStatus.NOT_FOUND_TEXT));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveUser(@RequestBody User request) {
        try {
            UserResponseDTO userResponseDTO = userService.save(request);
            return ResponseEntity.ok(new ApiResponse(CodeStatus.CREATED, CodeStatus.CREATED_TEXT, userResponseDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.BAD_REQUEST, null, e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public  ResponseEntity<ApiResponse> deleteUser(@PathVariable UUID id) {
        try {
            User user = userService.delete(id);
            UserResponseDTO userResponseDTO = new UserResponseDTO(user);
            return ResponseEntity.ok(new ApiResponse(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userResponseDTO));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(CodeStatus.BAD_REQUEST, null, e.getMessage()));
        }
    }
}
