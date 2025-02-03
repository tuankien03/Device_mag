package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;


import java.util.List;
import java.util.UUID;

public interface IUserDAO {
    UserResponseDTO findByUsername(String username);
    UserResponseDTO save(SaveUserRequestDTO saveUserRequestDTO);
    List<UserResponseDTO> findAll();
    UserResponseDTO findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
}
