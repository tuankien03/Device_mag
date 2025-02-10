package com.bkav.device_mag_backend.model.DTO.request;

import com.bkav.device_mag_backend.model.entity.DeviceStatus;

import java.util.UUID;

public class SaveDeviceRequestDTO {
    private UUID deviceId;
    private String name;
    private String description;
    private DeviceStatus status;
}
