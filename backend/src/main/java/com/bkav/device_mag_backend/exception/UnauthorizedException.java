package com.bkav.device_mag_backend.exception;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public class UnauthorizedException extends RuntimeException implements ToErrorResponseEntity {
    public UnauthorizedException(String message) {
        super(message);
    }

  @Override
  public ResponseEntity<ApiResponse<String>> toErrorResponseEntity() {
    return  ResponseEntity.status(CodeStatus.UNAUTHORIZED).body(new ApiResponse<>(CodeStatus.UNAUTHORIZED,getMessage(),null));
  }
}
