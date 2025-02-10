package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.UserMapper;
import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.exception.EntityNotFoundException;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserAuthenticationDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

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
        return userMapper.toUserAuthenticationDTO(user.get());
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
    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        users.stream().map(UserResponseDTO::new).forEach(userResponseDTOS::add);
        return userResponseDTOS;
    }
}