package com.bkav.device_mag_backend.exception;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler  {

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<ApiResponse> handle(Exception e) {
//        return ResponseEntity.status(CodeStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(CodeStatus.INTERNAL_SERVER_ERROR,"Hề Hề!!! em không xử lí nên không biết:))", null));
//    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException e) {
        return e.toErrorResponseEntity();
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> handleBadRequestException(BadRequestException e) {
        return e.toErrorResponseEntity();
    }

    /**
     * handle validation exception
     * @param e
     * @return ResponseEntity
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(CodeStatus.BAD_REQUEST,Objects.requireNonNull(e.getFieldError()).getDefaultMessage(), null));
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(CodeStatus.BAD_REQUEST,e.getCause().getMessage(),null));
    }

}


