package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDeviceDAO;
import com.bkav.device_mag_backend.service.interfaces.IUserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDeviceService implements IUserDeviceService {
    private final IUserDeviceDAO userDeviceDaoImpl;

    @Override
    public PageResponse<UserDeviceResponseDTO> findAllUserDevices(Pageable pageable) {
        return userDeviceDaoImpl.getAll(pageable);
    }

    @Override
    public PageResponse<UserDeviceResponseDTO> findAllByUserId(UUID userId, Pageable pageable) {
        return userDeviceDaoImpl.getUserDeviceByUserId(userId,pageable);
    }

    @Override
    public PageResponse<UserDeviceResponseDTO> findAllReturningUserDevices(Pageable pageable) {
        return userDeviceDaoImpl.getReturningUserDevices(pageable);
    }

}
