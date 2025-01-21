package com.bkav.device_mag_backend.response;

import com.bkav.device_mag_backend.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ApiResponse {
    int status;
    String message;
    Object body;

    public ApiResponse(int status, Object body , String message) {
        this.status = status;
        this.body = body;
        this.message = message;
    }
}
