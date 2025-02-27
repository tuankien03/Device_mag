package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.model.entity.UserRole;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class UserDeviceResponseDTO {
    private Long id;
    private String deviceName;
    private String descriptionDevice;
    private DeviceStatus statusDevice;
    private String username;
    private UserRole role;
    private UUID userId;
    private String deviceId;
    private LocalDateTime assignedAt;
    private LocalDateTime returnedAt;

    public UserDeviceResponseDTO() {}

}
