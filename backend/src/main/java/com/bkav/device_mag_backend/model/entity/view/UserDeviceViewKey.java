package com.bkav.device_mag_backend.model.entity.view;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDeviceViewKey {
    private UUID userId;
    private UUID deviceId;
}
