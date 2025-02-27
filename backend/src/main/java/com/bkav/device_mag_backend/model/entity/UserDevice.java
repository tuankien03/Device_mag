package com.bkav.device_mag_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Immutable
@Table(name = "user_device")
public class UserDevice {
    @Id
    @Column(name="id")
    private Long id;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "description_device")
    private String descriptionDevice;

    @Column(name = "status_device")
    @Enumerated(EnumType.STRING)
    private DeviceStatus statusDevice;

    @Column(name="username")
    private String username;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="user_id")
    private UUID userId;

    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

}
