package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {
}
