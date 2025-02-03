package com.bkav.device_mag_backend.model.DTO.response;

import lombok.Data;

@Data
public class ApiResponse {
    int status;
    String message;
    Object body;

    public ApiResponse(int status, String message, Object body ) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
