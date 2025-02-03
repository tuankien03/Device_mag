package com.bkav.device_mag_backend.model.DTO.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String role;
}
