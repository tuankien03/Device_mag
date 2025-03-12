package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class UserAuthentication {
    UUID userID;
    String username;
    String password;
    UserRole role;

    public UserAuthentication(User user) {
        this.userID = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
