package com.bkav.device_mag_backend.controller;


import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("{id}")
    public ApiResponse<DeviceResponseDTO>getDeviceById(@PathVariable UUID id) {
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.findDeviceById(id));
    }

    @GetMapping("search/{name}")
    public ApiResponse<PageResponse<DeviceResponseDTO>> getDeviceByName(
            @PathVariable String name,
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "createdAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.findDevicesByName(name, pageable));

    }

    @GetMapping
    public ApiResponse<PageResponse<DeviceResponseDTO>> getAllDevices(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "createdAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
    ) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.findAllDevices(pageable));
    }

    @GetMapping("available")
    public ApiResponse<PageResponse<DeviceResponseDTO>> getAllAvailableDevices(
            @RequestParam(value = "page" , required = false, defaultValue = "1") int page,
            @RequestParam(value="size", required = false, defaultValue = "12") int size,
            @RequestParam(value = "property", required = false, defaultValue = "createdAt") String property,
            @RequestParam(value = "direction", required = false, defaultValue = "ASC") String direction
    ) {
        Sort sort = Sort.by(property).descending();
        if (direction.equals("ASC")) {
            sort = Sort.by(property).ascending();
        }
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.findAvailableDevices(pageable));
    }

    @PostMapping
    public ApiResponse<DeviceResponseDTO> saveDevice(@RequestBody SaveDeviceRequestDTO request) {
        System.out.println(request);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.saveDevice(request));
    }

    @PutMapping("{id}")
    public ApiResponse<DeviceResponseDTO> updateDevice(@RequestBody SaveDeviceRequestDTO request, @PathVariable UUID id) {
        request.setDeviceId(id);
        System.out.println(request);
        return  new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT,deviceService.saveDevice(request));

    }

    @DeleteMapping("{id}")
    public ApiResponse<String> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDeviceById(id);
        return new ApiResponse<>(CodeStatus.SUCCESS, CodeStatus.SUCCESS_TEXT, "Deleted user");
    }
}
