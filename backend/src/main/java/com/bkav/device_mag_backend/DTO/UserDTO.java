package com.bkav.device_mag_backend.DTO;

import com.bkav.device_mag_backend.entity.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class UserDTO  {
    UUID id;
    String username;
    String role;
    String createdAt;
    String updatedAt;

    public UserDTO(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt().toString();
        this.updatedAt = user.getUpdatedAt().toString();
    }

}
