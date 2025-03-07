package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.service.UserService;
import com.bkav.device_mag_backend.service.interfaces.IUserDeviceService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
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
    private final IUserDeviceService userDeviceService;

    @Autowired
    public UserController(UserService userService, IUserDeviceService userDeviceService) {
        this.userService = userService;
        this.userDeviceService = userDeviceService;
    }

    @GetMapping
    public ApiResponse<PageResponse<UserResponseDTO>> getAllUsers(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "createdAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction,
            @RequestParam(value = "searchText", required = false, defaultValue = "") String username
    ) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,userService.findUsersByUsername(username, pageable));
    }

    @GetMapping("{id}")
    public ApiResponse<UserResponseDTO> getUser(@PathVariable UUID id) {
            return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.findUserById(id));
    }

    @GetMapping("search/{username}")
    public ApiResponse<PageResponse<UserResponseDTO>> getUserByUsername(
            @PathVariable String username,
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "createdAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,userService.findUsersByUsername(username, pageable));

    }

    @GetMapping("{id}/devices")
    public ApiResponse<PageResponse<UserDeviceResponseDTO>> getDevicesByUserId(
            @PathVariable UUID id,
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "assignedAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
    ) {
        Sort sort = Sort.by(property);
        if (direction.equals("DES")) {
            sort = Sort.by(property).descending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,userDeviceService.findAllByUserId(id, pageable));
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> saveUser(@RequestBody SaveUserRequestDTO request) {
        return new ApiResponse<>(CodeStatus.CREATED, CodeStatus.CREATED_TEXT,  userService.createUser(request));
    }

    @PutMapping("{id}")
    public ApiResponse<UserResponseDTO> updateUser(@PathVariable UUID id, @RequestBody SaveUserRequestDTO request) {
        request.setUserId(id);
        System.out.println("update user");
        System.out.println( request);
        System.out.println("update user");

        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, userService.updateUser(id,request));

    }

    @DeleteMapping("{id}")
    public  ApiResponse<String> deleteUser(@PathVariable UUID id) {
            userService.deleteUserById(id);
            return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, "Deleted user");
    }
}
