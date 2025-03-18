package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.model.entity.User;
import com.bkav.device_mag_backend.model.entity.UserDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, UUID> {
    Page<UserDevice> findAllByUserIdAndReturnedAtIsNull(UUID userId, Pageable pageable);
    Page<UserDevice> findAllByUserIdAndReturnedAtIsNotNull(UUID userId, Pageable pageable);
    Page<UserDevice> findAllByStatusDeviceAndReturnedAtIsNull(DeviceStatus statusDevice, Pageable pageable);
}
