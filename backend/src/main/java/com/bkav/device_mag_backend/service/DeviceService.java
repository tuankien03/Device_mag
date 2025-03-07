package com.bkav.device_mag_backend.service;

import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IDeviceDAO;
import com.bkav.device_mag_backend.service.interfaces.IDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeviceService implements IDeviceService {
    private final IDeviceDAO deviceDao;
    @Override
    public DeviceResponseDTO findDeviceById(UUID id) {
        return  deviceDao.getDeviceById(id);
    }

    @Override
    public DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO) {
        System.out.println(saveDeviceRequestDTO);
        return deviceDao.saveDevice(saveDeviceRequestDTO);
    }

    @Override
    public PageResponse<DeviceResponseDTO> findAllDevices(Pageable pageable) {
        return deviceDao.getAllDevices(pageable);
    }

    @Override
    public PageResponse<DeviceResponseDTO> findDevicesByName(String name, Pageable pageable) {
        return deviceDao.getDevicesByName(name, pageable);
    }

    @Override
    public PageResponse<DeviceResponseDTO> findAvailableDevices(Pageable pageable) {
        return deviceDao.getAvalableDevice(pageable);
    }

    @Override
    public boolean checkDeviceAvailability(UUID id) {
        DeviceResponseDTO device  = deviceDao.getDeviceById(id);
        if (device.getStatus().getValue().equals("ASSIGNED")) {
            return false;
        }
        if(device.getStatus().getValue().equals("RETURNING")) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteDeviceById(UUID id) {
        deviceDao.deleteDeviceById(id);
    }


}
