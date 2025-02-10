package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.view.UserDeviceView;
import com.bkav.device_mag_backend.model.entity.view.UserDeviceViewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeviceViewRepository extends JpaRepository<UserDeviceView, UserDeviceViewKey>{
}
