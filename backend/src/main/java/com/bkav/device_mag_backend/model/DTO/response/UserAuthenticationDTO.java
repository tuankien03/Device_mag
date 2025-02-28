package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class UserAuthenticationDTO {
    UUID userID;
    String username;
    String password;
    UserRole role;

    public UserAuthenticationDTO(User user) {
        this.userID = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
