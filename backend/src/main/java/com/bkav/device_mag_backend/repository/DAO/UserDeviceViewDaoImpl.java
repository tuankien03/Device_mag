package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDeviceViewDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserDeviceViewRepository;

import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;

@Component
public class UserDeviceViewDaoImpl implements IUserDeviceViewDAO {
    private final UserDeviceViewRepository userDeviceViewRepository;

    @Autowired
    public UserDeviceViewDaoImpl(UserDeviceViewRepository userDeviceViewRepository) {
        this.userDeviceViewRepository = userDeviceViewRepository;
    }


    public Page<UserResponseDTO> getAllUsers(Pageable pageable, Size size) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }
}
