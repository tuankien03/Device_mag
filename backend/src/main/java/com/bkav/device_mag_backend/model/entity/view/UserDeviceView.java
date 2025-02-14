package com.bkav.device_mag_backend.model.entity.view;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "user_device")
@Data
public class UserDeviceView {
    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride( name = "userId", column = @Column(name = "user_id")),
            @AttributeOverride( name = "deviceId", column = @Column(name = "device_id")),
    })
    private UserDeviceViewKey id;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name="description_device")
    private String descriptionDevice;

    @Column(name="status_device")
    private String statusDevice;

    @Column(name = "username")
    private String userName;

    @Column(name="role")
    private String role;


    @Column(name = "assigned_at")
    private String assignedAt;

    @Column(name = "returned_at")
    private String returnedAt;


}
