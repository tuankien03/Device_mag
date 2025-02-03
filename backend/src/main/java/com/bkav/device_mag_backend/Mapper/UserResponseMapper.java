package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements IMapper<User, UserResponseDTO>{
    @Override
    public User convertToEntity(UserResponseDTO userResponseDTO) {
        User user = new User();
        user.setUsername(userResponseDTO.getUsername());
        user.setRole(userResponseDTO.getRole());
        return user;
    }

    @Override
    public UserResponseDTO convertToDto(User user) {
        return new UserResponseDTO(user);
    }
}
