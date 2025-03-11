package com.bkav.device_mag_backend.repository.DAO;

import com.bkav.device_mag_backend.Mapper.UserDeviceMapper;
import com.bkav.device_mag_backend.model.DTO.response.PageResponse;
import com.bkav.device_mag_backend.model.DTO.response.UserDeviceResponseDTO;
import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.model.entity.UserDevice;
import com.bkav.device_mag_backend.repository.DAO.interfaces.IUserDeviceDAO;
import com.bkav.device_mag_backend.repository.JpaRepository.UserDeviceRepository;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDeviceDaoImpl implements IUserDeviceDAO {
    private final UserDeviceRepository userDeviceRepository;
    private final UserDeviceMapper userDeviceMapper;

    @Override
    public PageResponse<UserDeviceResponseDTO> getAll(Pageable pageable) {
        Page<UserDevice> userDevices = userDeviceRepository.findAll(pageable);
        return getUserDeviceResponseDTOPageResponse(pageable, userDevices);
    }

    @Override
    public PageResponse<UserDeviceResponseDTO> getUserDeviceByUserId(UUID userId, Pageable pageable) {
        Page<UserDevice> userDevices = userDeviceRepository.findAllByUserId(userId,pageable);
        return getUserDeviceResponseDTOPageResponse(pageable, userDevices);
    }

    @Override
    public PageResponse<UserDeviceResponseDTO> getReturningUserDevices(Pageable pageable) {
        Page<UserDevice> userDevices = userDeviceRepository.findAllByStatusDeviceAndReturnedAtIsNull(DeviceStatus.RETURNING,pageable);
        return getUserDeviceResponseDTOPageResponse(pageable, userDevices);
    }

    @Override
    public PageResponse<UserDeviceResponseDTO> getBorrowingUserDevices(Pageable pageable) {
        Page<UserDevice> userDevices = userDeviceRepository.findAllByStatusDeviceAndReturnedAtIsNull(DeviceStatus.ASSIGNED,pageable);
        return getUserDeviceResponseDTOPageResponse(pageable, userDevices);
    }


    private PageResponse<UserDeviceResponseDTO> getUserDeviceResponseDTOPageResponse(Pageable pageable, Page<UserDevice> userDevices) {
        System.out.println(userDevices.getTotalElements());
        List<UserDeviceResponseDTO> userDeviceResponseDTOS = new ArrayList<>();
        userDevices.getContent().forEach(userDevice -> {
            UserDeviceResponseDTO userDeviceResponseDTO = userDeviceMapper.toUserDeviceResponseDTO(userDevice);
            userDeviceResponseDTOS.add(userDeviceResponseDTO);
        });
        return  PageResponse.<UserDeviceResponseDTO>builder()
                .currentPage(pageable.getPageNumber() + 1)
                .totalElements(userDevices.getTotalElements())
                .totalPages(userDevices.getTotalPages() + 1)
                .pageSize(userDevices.getSize())
                .data(userDeviceResponseDTOS)
                .build();
    }
}
