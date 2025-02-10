package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.entity.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    Device toDeviceFromSaveDeviceDTO(SaveDeviceRequestDTO saveDeviceRequestDTO);
    DeviceResponseDTO toDeviceResponseDTO(Device device);
}
