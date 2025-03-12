package com.bkav.device_mag_backend.service;


import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.model.DTO.request.ChangePasswordRequestDTO;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthentication;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public UserAuthentication findUserByUsername(String username) {
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
    public UserResponseDTO updateUser(UUID id,@Valid SaveUserRequestDTO saveUserRequestDTO) {
        if(userDaoImpl.findById(id) == null) {
            throw new BadRequestException("Yêu cầu không hợp lệ!!");
        }
        System.out.println(userDaoImpl.findById(id));
        UserResponseDTO changingUser = userDaoImpl.findById(id);
        if (!saveUserRequestDTO.getUsername().equals(changingUser.getUsername())) {
            throw  new BadRequestException("Không thể sửa username!!");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getAuthorities());
        if (!saveUserRequestDTO.getRole().equals(changingUser.getRole())) {
            authentication.getAuthorities().forEach(a -> {
            System.out.println(a.getAuthority());
            if (a.getAuthority().equals("ROLE_USER")) {
                throw new BadRequestException("Không thể sửa role!!");
            }
        });
        }
        String encodedPassword = passwordEncoder.encode(saveUserRequestDTO.getPassword());
        saveUserRequestDTO.setPassword(encodedPassword);
        return userDaoImpl.save(saveUserRequestDTO);
    }



    @Override
    public UserResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthentication current_user = userDaoImpl.findUserByUsername(authentication.getName());
        if (!passwordEncoder.matches(changePasswordRequestDTO.getOldPassword(), current_user.getPassword())) {
            throw new BadRequestException("Mật khẩu cũ không đúng");
        }
        SaveUserRequestDTO saveUserRequestDTO = new SaveUserRequestDTO();
        saveUserRequestDTO.setUsername(current_user.getUsername());
        saveUserRequestDTO.setPassword(changePasswordRequestDTO.getNewPassword());
        saveUserRequestDTO.setRole(current_user.getRole());
        return updateUser(current_user.getUserID(),saveUserRequestDTO);
    }

    @Override
    public UserResponseDTO createUser(@Valid SaveUserRequestDTO saveUserRequestDTO) {
        if(userDaoImpl.checkIfUserExists(saveUserRequestDTO.getUsername())) {
            throw new BadRequestException("User đã tồi tại!!");
        }
        String encodedPassword = passwordEncoder.encode(saveUserRequestDTO.getPassword());
        System.out.println(encodedPassword);
        saveUserRequestDTO.setPassword(encodedPassword);

        return userDaoImpl.save(saveUserRequestDTO);
    }

    @Override
    public void deleteUserById(UUID id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAuthentication user = userDaoImpl.findUserByUsername(authentication.getName());
        if (user.getUserID().equals(id)) {
            throw new BadRequestException("Không thể xóa chính mình!!");
        }
        userDaoImpl.deleteById(id);
    }

    @Override
    public PageResponse<UserResponseDTO> findAllUsers(String username, Pageable pageable) {
        return userDaoImpl.findAll(username, pageable);
    }
}
