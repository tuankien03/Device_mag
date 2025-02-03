package com.bkav.device_mag_backend.service;


import com.bkav.device_mag_backend.exception.ResourceNotFoundException;
import com.bkav.device_mag_backend.repository.DAO.UserDaoImpl;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDAO;
import com.bkav.device_mag_backend.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bkav.device_mag_backend.model.entity.User;

import java.util.List;
import java.util.UUID;


/**
 * Service cho User
 */
@Service
public class UserService implements IUserService {

    private final IUserDAO userDaoImpl;

    @Autowired
    UserService(UserDaoImpl userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }


    @Override
    public List<User> findAll() {
        return userDaoImpl.findAll();
    }

    @Override
    public User findById(UUID uuid) {
        return userDaoImpl.findById(uuid);
    }

    @Override
    public User save(User user) {
        return userDaoImpl.save(user);
    }

    @Override
    public User delete(UUID uuid) {
        User user = findById(uuid);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        userDaoImpl.delete(user);

        return user;
    }


}
