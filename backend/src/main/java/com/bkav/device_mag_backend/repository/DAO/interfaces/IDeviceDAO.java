package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.entity.Device;

import java.util.List;
import java.util.UUID;

public interface IDeviceDAO {
    List<DeviceResponseDTO> getAllDevices();
    DeviceResponseDTO getDeviceById(UUID id);
    DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO);
    void deleteDeviceById(UUID id);
}
