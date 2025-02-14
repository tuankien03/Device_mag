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
}
