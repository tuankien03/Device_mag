package com.bkav.device_mag_backend.model.DTO.request;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
