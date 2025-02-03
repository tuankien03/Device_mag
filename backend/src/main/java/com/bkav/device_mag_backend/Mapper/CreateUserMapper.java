package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMapper implements IMapper<User, SaveUserRequestDTO> {

    @Override
    public User convertToEntity(SaveUserRequestDTO saveUserRequestDTO) {
        User user = new User();
        user.setUsername(saveUserRequestDTO.getUsername());
        user.setPassword(saveUserRequestDTO.getPassword());
        user.setRole(saveUserRequestDTO.getRole());
        return user;
    }

    @Override
    public SaveUserRequestDTO convertToDto(User user) {
        return new SaveUserRequestDTO(user);
    }
}
