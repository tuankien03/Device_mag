package com.bkav.device_mag_backend.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String role;
}
