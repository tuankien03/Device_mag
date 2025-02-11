package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import org.hibernate.engine.jdbc.Size;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;


public interface IUserDeviceViewDAO {
    public UserResponseDTO getUserById(Long id);
}
