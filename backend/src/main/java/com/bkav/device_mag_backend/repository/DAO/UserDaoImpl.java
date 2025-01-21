package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.entity.User;
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

    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw  new ResourceNotFoundException("User not found");
        }
        return user.get();
    }

    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new BadRequestException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(UUID uuid) {
        if(userRepository.findById(uuid).isEmpty()){
            throw  new ResourceNotFoundException("User not found");
        }
        return userRepository.findById(uuid).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}