package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserDeviceService {
    PageResponse<UserDeviceResponseDTO> findAllUserDevices(Pageable pageable);
    PageResponse<UserDeviceResponseDTO> findAllByUserId(UUID userId, Pageable pageable);
}
