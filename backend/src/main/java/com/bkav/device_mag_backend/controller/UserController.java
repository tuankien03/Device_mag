package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.service.UserService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponse<PageResponse<UserResponseDTO>> getAllUsers(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size
    ) {

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,userService.findAllUsers(pageable));
    }

    @GetMapping("{id}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable UUID id) {
            return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findUserById(id));
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> saveUser(@RequestBody @Valid SaveUserRequestDTO request) {
        return new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT,  userService.createUser(request));
    }

    @PutMapping("{id}")
    public ApiResponse<UserResponseDTO> updateUser(@PathVariable UUID id,SaveUserRequestDTO request) {
        request.setUserId(id);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.updateUser(id,request));

    }

    @DeleteMapping("{id}")
    public  ApiResponse<String> deleteUser(@PathVariable UUID id) {
            userService.deleteUserById(id);
            return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, "Deleted user");
    }
}
