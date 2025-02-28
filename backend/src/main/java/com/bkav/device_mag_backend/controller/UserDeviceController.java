package com.bkav.device_mag_backend.controller;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.service.interfaces.IUserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-device")
@RequiredArgsConstructor
public class UserDeviceController {
    private final IUserDeviceService userDeviceService;

    @GetMapping
    public ApiResponse<PageResponse<UserDeviceResponseDTO>> getAllUsers(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "assignedAt") String property,
                        @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
    ) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,userDeviceService.findAllUserDevices(pageable));
    }


}
