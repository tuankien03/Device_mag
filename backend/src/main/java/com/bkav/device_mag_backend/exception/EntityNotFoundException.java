package com.bkav.device_mag_backend.exception;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class EntityNotFoundException extends RuntimeException implements ToErrorResponseEntity{
    public EntityNotFoundException(String message) {
        super(message);
    }


    @Override
    public ResponseEntity<ApiResponse<String>> toErrorResponseEntity() {
        return  ResponseEntity.status(CodeStatus.NOT_FOUND).body(new ApiResponse<>(CodeStatus.NOT_FOUND, Objects.requireNonNull(getMessage()), null));
    }
}
