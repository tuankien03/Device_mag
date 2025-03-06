package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.UserMapper;
import com.bkav.device_mag_backend.exception.EntityNotFoundException;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDAO {
    private final UserRepository  userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean checkIfUserExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public UserAuthenticationDTO findUserByUsername(String username) {
        User user =  userRepository.findByUsername(username).isPresent() ? userRepository.findByUsername(username).get() : null;
       if (user == null) {
           throw new EntityNotFoundException("User not found");
       }
        return new UserAuthenticationDTO(user);
    }

    @Override
    public PageResponse<UserResponseDTO> findUsersByUsername(String username, Pageable pageable) {
        Page<User> pageData = userRepository.findUsersByUsernameContaining(username,pageable);
        System.out.println(pageData);
        return getUserResponseDTOPageResponse(pageable, pageData);
    }

    private PageResponse<UserResponseDTO> getUserResponseDTOPageResponse(Pageable pageable, Page<User> pageData) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        pageData.getContent().stream().map(UserResponseDTO::new).forEach(userResponseDTOS::add);
        System.out.println(userResponseDTOS);
        return PageResponse.<UserResponseDTO>builder()
                .currentPage(pageable.getPageNumber() + 1)
                .totalElements((int) pageData.getTotalElements())
                .totalPages(pageData.getTotalPages() )
                .pageSize(pageData.getSize())
                .data(userResponseDTOS)
                .build();
    }

    public UserResponseDTO save(SaveUserRequestDTO saveUserRequestDTO) {
        User user = null;
        if (checkIfUserExists(saveUserRequestDTO.getUsername())) {
            user = userRepository.findByUsername(saveUserRequestDTO.getUsername()).get();
            user.setPassword(saveUserRequestDTO.getPassword());
            user.setRole(saveUserRequestDTO.getRole());
            user.setUpdatedAt(LocalDateTime.now());
        } else {
            user = userMapper.toUserFromSaveUserResquestDTO(saveUserRequestDTO);
        }
        return new UserResponseDTO(userRepository.save(user));
    }



    @Override
    public UserResponseDTO findById(UUID uuid) {
        if(userRepository.findById(uuid).isEmpty()){
            throw  new EntityNotFoundException("User not found");
        }
        return new UserResponseDTO(userRepository.findById(uuid).get());
    }

    @Override
    public void deleteById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public PageResponse<UserResponseDTO> findAll(String username, Pageable pageable) {
        Page<User> pageData = userRepository.findUsersByUsernameContaining(username, pageable);
        System.out.println(pageData);
        return getUserResponseDTOPageResponse(pageable, pageData);
    }
}