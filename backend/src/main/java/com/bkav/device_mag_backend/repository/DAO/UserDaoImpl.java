package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.CreateUserMapper;
import com.bkav.device_mag_backend.Mapper.UserResponseMapper;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.exception.BadRequestException;
import com.bkav.device_mag_backend.exception.ResourceNotFoundException;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserDaoImpl implements IUserDAO {

    private final UserRepository  userRepository;
    private final CreateUserMapper createUserMapper;
    private final UserResponseMapper userResponseMapper;


    @Autowired
    public UserDaoImpl(UserRepository userRepository, CreateUserMapper createUserMapper, UserResponseMapper userResponseMapper) {
        this.userRepository = userRepository;
        this.createUserMapper = createUserMapper;
        this.userResponseMapper = userResponseMapper;
    }






    @Override
    public UserResponseDTO findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw  new ResourceNotFoundException("User not found");
        }
        return new UserResponseDTO(user.get());
    }

    public UserResponseDTO save(SaveUserRequestDTO saveUserRequestDTO) {
        User user = createUserMapper.convertToEntity(saveUserRequestDTO);
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        return new UserResponseDTO(userRepository.save(user));
    }




    @Override
    public void delete(Object o) {

    }

    @Override
    public UserResponseDTO findById(UUID uuid) {
        if(userRepository.findById(uuid).isEmpty()){
            throw  new ResourceNotFoundException("User not found");
        }
        return userRepository.findById(uuid).get();
    }

    @Override
    public void deleteById(UUID id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll();
    }
}