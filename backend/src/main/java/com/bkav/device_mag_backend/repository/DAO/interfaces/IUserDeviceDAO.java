package com.bkav.device_mag_backend.repository.DAO.interfaces;

import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IUserDeviceDAO {
    PageResponse<UserDeviceResponseDTO> getAll(Pageable pageable);
}
