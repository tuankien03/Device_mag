package com.bkav.device_mag_backend.exception;

import Constant.CodeStatus;
import com.bkav.device_mag_backend.model.DTO.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler  {

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

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<String>> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMessage = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ApiResponse<>(CodeStatus.BAD_REQUEST, errorMessage, null));
    }



    @ExceptionHandler(value = PropertyReferenceException.class)
    public ResponseEntity<ApiResponse<String>> handlePropertyReferenceException(PropertyReferenceException e) {
        return ResponseEntity.badRequest().body(new ApiResponse<>(CodeStatus.BAD_REQUEST, e.getMessage(), null));
    }



    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentTypeMismatchException e) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(CodeStatus.BAD_REQUEST,e.getCause().getMessage(),null));
    }

}


