package com.bkav.device_mag_backend.model.DTO.response;

import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
public class UserAuthenticationDTO {
    String username;
    String password;
    UserRole role;

    public UserAuthenticationDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
