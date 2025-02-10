package com.bkav.device_mag_backend.exception;

import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface ToErrorResponseEntity {
    public ResponseEntity<ApiResponse<String>> toErrorResponseEntity();
}
