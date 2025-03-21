package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.DeviceMapper;
import com.bkav.device_mag_backend.exception.EntityNotFoundException;
import com.bkav.device_mag_backend.model.DTO.request.SaveDeviceRequestDTO;
import com.bkav.device_mag_backend.model.DTO.response.DeviceResponseDTO;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.entity.Device;
import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IDeviceDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DeviceDaoImpl implements IDeviceDAO {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public PageResponse<DeviceResponseDTO> getAllDevices(String deviceName, Pageable pageable) {
        Page<Device> pageData = deviceRepository.findAllByNameContaining(deviceName, pageable);
        System.out.println(pageData.getContent());
        return PageResponse.<DeviceResponseDTO>builder()
                .totalPages(pageData.getTotalPages())
                .totalElements((int) pageData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .pageSize(pageData.getSize())
                .data(pageData.getContent().stream().map(deviceMapper::toDeviceResponseDTO).collect(Collectors.toList()))
                .build();
    }

    @Override
    public PageResponse<DeviceResponseDTO> getDevicesByName(String name, Pageable pageable) {
        Page<Device> pageData = deviceRepository.findDevicesByNameContaining(name, pageable);
        System.out.println(pageData.getContent());
        return PageResponse.<DeviceResponseDTO>builder()
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .pageSize(pageData.getSize())
                .data(pageData.getContent().stream().map(deviceMapper::toDeviceResponseDTO).collect(Collectors.toList()))
                .build();    }

    @Override
    public PageResponse<DeviceResponseDTO> getAvalableDevice(Pageable pageable) {
        Page<Device> pageData = deviceRepository.findByStatus(DeviceStatus.AVAILABLE, pageable);
        System.out.println(pageData.getContent());
        return PageResponse.<DeviceResponseDTO>builder()
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .currentPage(pageable.getPageNumber() + 1)
                .pageSize(pageData.getSize())
                .data(pageData.getContent().stream().map(deviceMapper::toDeviceResponseDTO).collect(Collectors.toList()))
                .build();
    }


    @Override
    public DeviceResponseDTO getDeviceById(UUID id) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device == null) {
            throw new EntityNotFoundException("Thiết bị không tồn tại!!");
        }
        return deviceMapper.toDeviceResponseDTO(device);
    }

    @Override
    public DeviceResponseDTO saveDevice(SaveDeviceRequestDTO saveDeviceRequestDTO) {
        Device device = deviceMapper.toDeviceFromSaveDeviceRequestDTO(saveDeviceRequestDTO);
        System.out.println("test");
        System.out.println(device);
        return deviceMapper.toDeviceResponseDTO(deviceRepository.save(device));
    }

    @Override
    public void deleteDeviceById(UUID id) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device == null) {
            throw new EntityNotFoundException("Thiết bị không tồn tại!!");
        }
        deviceRepository.delete(device);
    }
}
