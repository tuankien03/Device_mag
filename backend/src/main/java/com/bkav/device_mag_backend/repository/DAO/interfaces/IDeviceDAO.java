package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.entity.Device;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDeviceDAO {
    PageResponse<DeviceResponseDTO> getAllDevices(Pageable pageable);
    PageResponse<DeviceResponseDTO> getDevicesByName(String name,Pageable pageable);
    DeviceResponseDTO getDeviceById(UUID id);
    DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO);
    void deleteDeviceById(UUID id);
}
