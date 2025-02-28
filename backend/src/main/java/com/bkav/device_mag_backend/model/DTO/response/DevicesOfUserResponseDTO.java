package com.bkav.device_mag_backend.model.DTO.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DevicesOfUserResponseDTO {
    UUID deviceId;
    String deviceName;
    String deviceStatus;
    LocalDateTime assignedAt;
    LocalDateTime returnedAt;
}
