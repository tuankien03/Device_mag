package com.bkav.device_mag_backend.model.entity;

import com.bkav.device_mag_backend.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DeviceStatus {
    AVAILABLE("AVAILABLE"),
    RETURNING("RETURNING"),
    ASSIGNED("ASSIGNED"),;

    private final String value;

    DeviceStatus(String value) {
        this.value = value;
    }


    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static DeviceStatus forValue(String value) {
        for (DeviceStatus status : DeviceStatus.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new BadRequestException("Giá trị không hợp lệ: " + value);
    }

}
