package com.bkav.device_mag_backend.exception;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BadRequestException extends RuntimeException implements ToErrorResponseEntity{
  public BadRequestException(String message) {
    super(message);
  }

  @Override
  public ResponseEntity<ApiResponse<String>> toErrorResponseEntity() {
    return  ResponseEntity.status(CodeStatus.BAD_REQUEST).body(new ApiResponse<>(CodeStatus.BAD_REQUEST,getMessage(),null));
  }
}
