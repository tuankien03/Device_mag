package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.DeviceMapper;
import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IDeviceDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceDaoImpl implements IDeviceDAO {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public List<DeviceResponseDTO> getAllDevices() {
        return List.of();
    }

    @Override
    public DeviceResponseDTO getDeviceById(UUID id) {
        return null;
    }

    @Override
    public DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO) {
        return null;
    }

    @Override
    public void deleteDeviceById(UUID id) {

    }
}
