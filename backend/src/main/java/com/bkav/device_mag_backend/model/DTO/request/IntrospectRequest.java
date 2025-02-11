package com.bkav.device_mag_backend.model.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntrospectRequest {
    private String token;
}
