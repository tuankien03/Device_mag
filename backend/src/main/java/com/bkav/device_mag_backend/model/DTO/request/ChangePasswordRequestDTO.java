package com.bkav.device_mag_backend.model.DTO.request;

import com.bkav.device_mag_backend.model.entity.UserRole;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Data
@Builder
public class ChangePasswordRequestDTO {
    private String oldPassword;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Cần ít nhất một chữ hoa, một chữ thường, một số, một kí tự đặc biệt và độ dài tối thiểu là 8 kí tự!")
    private String newPassword;


}
