package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.service.UserService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserController {
    private final IUserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponseDTO>> getAllUsers() {
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findAllUsers());
    }

    @GetMapping("{id}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable UUID id) {
            return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDTO>> saveUser(@RequestBody @Valid SaveUserRequestDTO request) {
        return ResponseEntity.ok(new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT,  userService.createUser(request)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable UUID id,SaveUserRequestDTO request) {
        request.setUserId(id);
        return ResponseEntity.ok(new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.updateUser(id,request)));

    }

    @DeleteMapping("{id}")
    public  ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable UUID id) {
            userService.deleteUserById(id);
            return ResponseEntity.ok(new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, "Deleted user"));
    }
}
