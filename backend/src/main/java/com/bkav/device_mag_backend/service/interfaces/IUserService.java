package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import java.util.List;
import java.util.UUID;

public interface IUserService  {
    public UserResponseDTO findUserById(UUID id);
    public UserAuthenticationDTO findUserByUsername(String username);
    public UserResponseDTO findByUsername(String username);
    public UserResponseDTO updateUser(UUID id, SaveUserRequestDTO saveUserRequestDTO);
    public UserResponseDTO createUser(SaveUserRequestDTO saveUserRequestDTO);
    public void deleteUserById(UUID id);
    public List<UserResponseDTO> findAllUsers();
}
