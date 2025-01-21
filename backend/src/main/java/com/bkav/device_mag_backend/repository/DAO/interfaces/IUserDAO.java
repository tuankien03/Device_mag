package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserDAO extends IDAO<User, UUID> {
    User findByUsername(String username);
}
