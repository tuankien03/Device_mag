package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.UserDeviceDaoImpl;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDeviceDAO;
import com.bkav.device_mag_backend.service.interfaces.IUserDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeviceService implements IUserDeviceService {
    private final IUserDeviceDAO userDeviceDaoImpl;

    @Override
    public PageResponse<UserDeviceResponseDTO> findAllUserDevices(Pageable pageable) {
        return userDeviceDaoImpl.getAll(pageable);
    }

}
