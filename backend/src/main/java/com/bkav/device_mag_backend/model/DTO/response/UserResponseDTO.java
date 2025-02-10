package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponseDTO {
    UUID id;
    String username;
    UserRole role;
    String createdAt;
    String updatedAt;

    public UserResponseDTO(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt().toString();
        this.updatedAt = user.getUpdatedAt().toString();
    }

}
