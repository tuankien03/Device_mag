package com.bkav.device_mag_backend.model.DTO.request;

import com.bkav.device_mag_backend.model.entity.User;
import lombok.Data;

@Data
public class SaveUserRequestDTO {
    private String username;
    private String password;
    private String role;

    public SaveUserRequestDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
