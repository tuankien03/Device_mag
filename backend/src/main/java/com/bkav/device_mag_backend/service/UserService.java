package com.bkav.device_mag_backend.service;


import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * Service cho User
 */
@Service
public class UserService implements IUserService {

    private final IUserDAO userDaoImpl;

    @Autowired
    UserService(IUserDAO userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }


    @Override
    public UserResponseDTO findUserById(UUID id) {
        return userDaoImpl.findById(id);
    }

    @Override
    public UserAuthenticationDTO findUserByUsername(String username) {
        return userDaoImpl.findUserByUsername(username);
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        return userDaoImpl.findByUsername(username);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, SaveUserRequestDTO saveUserRequestDTO) {
        if(userDaoImpl.findById(id) == null) {
            throw new BadRequestException("Yêu cầu không hợp lệ!!");
        }
        return userDaoImpl.save(saveUserRequestDTO);
    }

    @Override
    public UserResponseDTO createUser(SaveUserRequestDTO saveUserRequestDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(6);

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
    public List<UserResponseDTO> findAllUsers() {
        return userDaoImpl.findAll();
    }
}
