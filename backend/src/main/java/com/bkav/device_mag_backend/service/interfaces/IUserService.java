package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.ChangePasswordRequestDTO;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthentication;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface IUserService  {
    UserResponseDTO findUserById(UUID id);
    UserAuthentication findUserByUsername(String username);
    PageResponse<UserResponseDTO> findUsersByUsername(String username,Pageable pageable);
    UserResponseDTO updateUser(UUID id,@Valid SaveUserRequestDTO saveUserRequestDTO);
    UserResponseDTO changePassword(@Valid ChangePasswordRequestDTO changePasswordRequestDTO);
    UserResponseDTO createUser(@Valid SaveUserRequestDTO saveUserRequestDTO);
    void deleteUserById(UUID id);
    PageResponse<UserResponseDTO> findAllUsers(String username, Pageable pageable);
}
