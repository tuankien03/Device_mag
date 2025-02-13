package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService  {
    UserResponseDTO findUserById(UUID id);
    UserAuthenticationDTO findUserByUsername(String username);
    UserResponseDTO findByUsername(String username);
    UserResponseDTO updateUser(UUID id, SaveUserRequestDTO saveUserRequestDTO);
    UserResponseDTO createUser(SaveUserRequestDTO saveUserRequestDTO);
    void deleteUserById(UUID id);
    PageResponse<UserResponseDTO> findAllUsers(Pageable pageable);
}
