package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.request.SaveUserRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IDeviceService {
    DeviceResponseDTO findDeviceById(UUID id);
    DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO);
    PageResponse<DeviceResponseDTO> findAllDevices(Pageable pageable);
    PageResponse<DeviceResponseDTO> findDevicesByName(String name,Pageable pageable);
    boolean checkDeviceAvailability(UUID id);
    void deleteDeviceById(UUID id);
}
