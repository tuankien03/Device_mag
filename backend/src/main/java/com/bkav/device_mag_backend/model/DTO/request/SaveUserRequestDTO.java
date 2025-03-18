package com.bkav.device_mag_backend.model.DTO.request;

import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserRole;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class SaveUserRequestDTO {
    private UUID userId;

    @NotBlank(message = "username không được để trống")
    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "username chỉ bao gồm chữ thường và số độ dài từ 8 đến 16 kí tự")
    private String username;

    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Cần ít nhất một chữ hoa, một chữ thường, một số, một kí tự đặc biệt và độ dài tối thiểu là 8 kí tự!")
    private String password;

    @NotNull(message = "role không được để trống")
    private UserRole role;

    public SaveUserRequestDTO () {}

    public SaveUserRequestDTO(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
