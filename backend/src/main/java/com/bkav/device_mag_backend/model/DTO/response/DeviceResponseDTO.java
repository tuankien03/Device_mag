package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeviceResponseDTO {
    private UUID deviceId;
    private String name;
    private String description;
    private DeviceStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
