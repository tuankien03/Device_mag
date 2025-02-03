package com.bkav.device_mag_backend.service.interfaces;

import com.bkav.device_mag_backend.model.DTO.response.UserResponseDTO;
import com.bkav.device_mag_backend.model.entity.User;

import java.util.UUID;

public interface IUserService extends IService<UserResponseDTO, UUID> {

}
