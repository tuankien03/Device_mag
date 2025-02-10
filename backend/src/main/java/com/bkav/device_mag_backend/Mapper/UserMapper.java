package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUserFromSaveUserResquestDTO(SaveUserRequestDTO saveUserRequestDTO);
    UserResponseDTO toUserResponseDTO(User user);
    UserAuthenticationDTO toUserAuthenticationDTO(User user);
}
