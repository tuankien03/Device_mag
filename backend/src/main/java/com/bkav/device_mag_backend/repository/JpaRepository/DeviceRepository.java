package com.bkav.device_mag_backend.repository.JpaRepository;

import com.bkav.device_mag_backend.model.entity.Device;
import com.bkav.device_mag_backend.model.entity.DeviceStatus;
import com.bkav.device_mag_backend.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    @Query("SELECT d FROM Device d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Device> findDevicesByNameContaining(@Param("name") String name, Pageable pageable);
    Page<Device> findByStatus(DeviceStatus status, Pageable pageable);
    @Query("SELECT d FROM Device d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Device> findAllByNameContaining(String name, Pageable pageable);
}
