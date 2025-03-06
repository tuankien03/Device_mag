package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface IUserDAO {
    boolean checkIfUserExists(String username);
    UserAuthenticationDTO findUserByUsername(String username);
    PageResponse<UserResponseDTO>  findUsersByUsername(String username,Pageable pageable);
    UserResponseDTO save(SaveUserRequestDTO saveUserRequestDTO);
    PageResponse<UserResponseDTO> findAll(String username, Pageable pageable);
    UserResponseDTO findById(UUID id);
    void deleteById(UUID id);
    void deleteAll();
}
