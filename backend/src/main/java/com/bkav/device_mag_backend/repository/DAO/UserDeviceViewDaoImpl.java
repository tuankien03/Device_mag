package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.view.UserDeviceView;
import com.bkav.device_mag_backend.model.entity.view.UserDeviceViewKey;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDeviceViewDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserDeviceViewRepository;

import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Component;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class UserDeviceViewDaoImpl implements IUserDeviceViewDAO {
    private final UserDeviceViewRepository userDeviceViewRepository;

    @Autowired
    public UserDeviceViewDaoImpl(UserDeviceViewRepository userDeviceViewRepository) {
        this.userDeviceViewRepository = userDeviceViewRepository;
    }


    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable, Size size) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }
}
