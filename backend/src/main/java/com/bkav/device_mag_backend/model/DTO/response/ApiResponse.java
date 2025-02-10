package com.bkav.device_mag_backend.model.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int status;
    private String message;
    private T body;

    public ApiResponse(int status, String message, T body ) {
        this.status = status;
        this.message = message;
        this.body = body;
    }
}
