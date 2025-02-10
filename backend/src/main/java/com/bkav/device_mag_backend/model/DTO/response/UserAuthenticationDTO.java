package com.bkav.device_mag_backend.model.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
public class UserAuthenticationDTO {
    String username;
    String password;
}
