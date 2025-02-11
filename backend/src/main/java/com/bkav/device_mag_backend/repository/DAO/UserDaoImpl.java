package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.UserMapper;
import com.bkav.device_mag_backend.exception.BadRequestException;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements IUserDAO {
    private final UserRepository  userRepository;
    private final UserMapper userMapper;


    @Override
    public UserAuthenticationDTO findUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw  new EntityNotFoundException("User not found");
        }
        return new UserAuthenticationDTO(user.get());
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw  new EntityNotFoundException("User not found");
        }
        return new UserResponseDTO(user.get());
    }

    public UserResponseDTO save(SaveUserRequestDTO saveUserRequestDTO) {
        User user = userMapper.toUserFromSaveUserResquestDTO(saveUserRequestDTO);
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
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
    public PageResponse<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> pageData = userRepository.findAll(pageable);
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        pageData.getContent().stream().map(UserResponseDTO::new).forEach(userResponseDTOS::add);
        return PageResponse.<UserResponseDTO>builder()
                .currentPage(pageable.getPageNumber())
                .totalElements(pageData.getNumberOfElements())
                .totalPages(pageData.getTotalPages())
                .pageSize(pageData.getSize())
                .data(userResponseDTOS)
                .build();
    }
}