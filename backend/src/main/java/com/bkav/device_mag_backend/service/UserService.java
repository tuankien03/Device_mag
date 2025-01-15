package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.DTO.UserDTO;
import com.bkav.device_mag_backend.entity.User;
import com.bkav.device_mag_backend.repository.DAO.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service cho User
 */
@Service
public class UserService {

    private UserDaoImpl userDaoImpl;


    public List<UserDTO> findAll() {
        List<User> users = (List<User>) userDaoImpl.findAll();
        return users.stream().map(UserDTO::new).toList();

    }

    public UserDTO findById(UUID id) {
        if (id == null) {
            return null;
        }
        Optional<User> user = userDaoImpl.findById(id);
        return user.map(UserDTO::new).orElse(null);

    }

    public UserDTO save(User user) {
        return new UserDTO(userDaoImpl.save(user));
    }

    public void deleteById(UUID id) {
        if (id == null) {
            return;
        }
        Optional<User> user = userDaoImpl.findById(id);
        user.ifPresent(userDaoImpl::delete);
    }
}
