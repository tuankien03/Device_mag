package com.bkav.device_mag_backend.Mapper;

import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.model.entity.UserDevice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDeviceMapper {
    UserDeviceResponseDTO toUserDeviceResponseDTO(UserDevice userDevice);
}
