package com.bkav.device_mag_backend.service;


import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DevicesOfUserResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.service.interfaces.IAuthenticationService;
import com.bkav.device_mag_backend.service.interfaces.IDeviceService;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;


/**
 * Service cho User
 */
@Service
@RequiredArgsConstructor
@Validated
public class UserService implements IUserService {

    private final IUserDAO userDaoImpl;
    private final PasswordEncoder passwordEncoder ;


    @Override
    public UserResponseDTO findUserById(UUID id) {
        return userDaoImpl.findById(id);
    }

    @Override
    public UserAuthenticationDTO findUserByUsername(String username) {
        if (userDaoImpl.findUserByUsername(username) == null) {
            throw new BadRequestException("Username không tồn tại");
        }
        return userDaoImpl.findUserByUsername(username);
    }

    @Override
    public PageResponse<UserResponseDTO> findUsersByUsername(String username, Pageable pageable) {
        return userDaoImpl.findUsersByUsername(username, pageable);
    }


    @Override
    public UserResponseDTO updateUser(UUID id, SaveUserRequestDTO saveUserRequestDTO) {
        if(userDaoImpl.findById(id) == null) {
            throw new BadRequestException("Yêu cầu không hợp lệ!!");
        }
        return userDaoImpl.save(saveUserRequestDTO);
    }

    @Override
    public UserResponseDTO createUser(@Valid SaveUserRequestDTO saveUserRequestDTO) {
        String encodedPassword = passwordEncoder.encode(saveUserRequestDTO.getPassword());
        System.out.println(encodedPassword);
        saveUserRequestDTO.setPassword(encodedPassword);

        return userDaoImpl.save(saveUserRequestDTO);
    }

    @Override
    public void deleteUserById(UUID id) {
        userDaoImpl.deleteById(id);
    }

    @Override
    public PageResponse<UserResponseDTO> findAllUsers(Pageable pageable) {
        return userDaoImpl.findAll(pageable);
    }
}
