package com.bkav.device_mag_backend.model.DTO.request;

import com.bkav.device_mag_backend.model.entity.Device;
import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class SaveDeviceRequestDTO {
    private UUID deviceId;
    private String name;
    private String description;
    private DeviceStatus status;

    public SaveDeviceRequestDTO() {

    }

    public SaveDeviceRequestDTO(Device device) {
        this.deviceId = device.getDeviceId();
        this.name = device.getName();
        this.description = device.getDescription();
        this.status = device.getStatus();
    }
}
