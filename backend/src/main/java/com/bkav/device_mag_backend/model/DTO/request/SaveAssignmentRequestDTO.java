package com.bkav.device_mag_backend.model.DTO.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class SaveAssignmentRequestDTO {
    private UUID id;
    private UUID userId;
    private UUID deviceId;
    private LocalDateTime returnedAt;

    public SaveAssignmentRequestDTO(UUID id, UUID userId, UUID deviceId, LocalDateTime returnedAt) {
        this.id = id;
        this.userId = userId;
        this.deviceId = deviceId;
        this.returnedAt = returnedAt;
    }

    public SaveAssignmentRequestDTO() {}

}
